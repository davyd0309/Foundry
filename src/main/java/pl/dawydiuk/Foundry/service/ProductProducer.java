package pl.dawydiuk.Foundry.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import models.ProductRQ;
import models.ProductRS;
import models.dto.ProductDto;
import models.enums.ProductType;
import org.springframework.transaction.annotation.Transactional;
import pl.dawydiuk.Foundry.builder.ProductBuilder;
import pl.dawydiuk.Foundry.consumer.CreateProductStrategy;
import pl.dawydiuk.Foundry.consumer.ProductSynchronizationConsumer;
import pl.dawydiuk.Foundry.predicate.MassPredicate;

import java.util.List;


@AllArgsConstructor
@Slf4j
public class ProductProducer {

    private final OrderProducer orderProducer;
    private final ProductBuilder productBuilder;
    private final CreateProductStrategy createProductStrategy;
    private final MassPredicate massPredicate;
    private final ProductSynchronizationConsumer productSynchronizationConsumer;

    @Transactional
    public ProductRS createProduct(final List<ProductRQ> productRQ) {

        ProductRS productRS = new ProductRS();
        if (!productRQ.isEmpty()) {
            if (!isEnoughInStorage(productRQ)) {
                sendMessageMassAbsence(calculationOfDemand(productRQ));//TODO for unit test
            } else {
                production(productRQ, productRS);
                return productRS;
            }
        }
        return productRS;
    }

    private void production(final List<ProductRQ> productRQ, final ProductRS productRS) {
        productRQ.forEach(rq -> {
            for (int i = 0; i < rq.getNumber(); i++) {
                Product newProduct = buildNewProduct(rq.getType());//TODO what if type is NULL
                productionProcess(newProduct);
                buildResponse(productRS, newProduct);
            }
        });
    }

    private void productionProcess(final Product newProduct) {
        createProductStrategy.accept(newProduct);
    }

    private Product buildNewProduct(final ProductType productType) {
        return productBuilder.createNewProduct(productType);
    }

    private void buildResponse(final ProductRS productRS, final Product newProduct) {
        productRS.getProductsList().add(mapToDto(newProduct));
    }

    private ProductDto mapToDto(final Product product) {
            ProductDto productDto = new ProductDto();
            productDto.setType(product.getName());
            productDto.setId(product.getId());
            return productDto;
    }

    private boolean isEnoughInStorage(final List<ProductRQ> productRQ) {
        return massPredicate.test(productRQ);
    }


    private void sendMessageMassAbsence(final Double calculationOfDemand) {
        orderProducer.accept(calculationOfDemand);
    }

    private List<Product> synchronizationProductsToBeMade(final List<ProductRQ> productRQ) {
        return productSynchronizationConsumer.apply(productRQ);
    }

    private double calculationOfDemand(final List<ProductRQ> productRQS) {
        return productRQS.stream()
                .map(ProductRQ::getType)
                .mapToDouble(ProductType::getAmountOfMass)
                .sum();
    }
}

package pl.dawydiuk.Foundry.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.AddProduct;
import models.Product;
import models.ProductCreateRQ;
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
    public ProductRS createProduct(final ProductCreateRQ productCreateRQ) {

        ProductRS productRS = new ProductRS();
        if (!productCreateRQ.getProductList().isEmpty()) {
            if (!isEnoughInStorage(productCreateRQ)) {
                sendMessageMassAbsence(calculationOfDemand(productCreateRQ));
            } else {
                production(productCreateRQ, productRS);
                return productRS;
            }
        }
        return productRS;
    }

    private void production(ProductCreateRQ productCreateRQ, final ProductRS productRS) {
        productCreateRQ.getProductList().forEach(rq -> {
            for (int i = 0; i < rq.getNumber(); i++) {
                Product newProduct = buildNewProduct(rq.getType());
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

    private boolean isEnoughInStorage(ProductCreateRQ productCreateRQ) {
        return massPredicate.test(productCreateRQ);
    }


    private void sendMessageMassAbsence(final Double calculationOfDemand) {
        orderProducer.accept(calculationOfDemand);
    }

    private List<Product> synchronizationProductsToBeMade(ProductCreateRQ productCreateRQ) {
        return productSynchronizationConsumer.apply(productCreateRQ);
    }

    private double calculationOfDemand(ProductCreateRQ productCreateRQ) {
        return productCreateRQ.getProductList().stream()
                .map(AddProduct::getType)
                .mapToDouble(ProductType::getAmountOfMass)
                .sum();
    }
}

package pl.dawydiuk.Foundry.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.CreateProductRQ;
import models.Product;
import models.ProductRS;
import models.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;
import pl.dawydiuk.Foundry.builder.ProductBuilder;
import pl.dawydiuk.Foundry.consumer.CreateProductStrategy;
import pl.dawydiuk.Foundry.consumer.ProductSynchronizationConsumer;
import pl.dawydiuk.Foundry.mapper.ProductDtoMapper;
import pl.dawydiuk.Foundry.predicate.MassPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Slf4j
public class ProductProducer {

    private final OrderProducer orderProducer;
    private final ProductBuilder productBuilder;
    private final CreateProductStrategy createProductStrategy;
    private final MassPredicate massPredicate;
    private final ProductSynchronizationConsumer productSynchronizationConsumer;

    @Transactional
    public ProductRS createProduct(final List<CreateProductRQ> createProductRQ) {
        final List<CreateProductRQ> productList = Optional.ofNullable(createProductRQ).orElse(new ArrayList<CreateProductRQ>());
        ProductRS productRS = new ProductRS();
        if (!productList.isEmpty()) {
            List<Product> productsToBeMade = synchronizationProductsToBeMade(productList);
            if (isNotEnoughInStorage(productsToBeMade)) {
                sendMessageMassAbsence();
            } else {
                for (int productId = 0; productId < productsToBeMade.size(); productId++) {
                    Product newProduct = buildNewProduct();
                    Optional.of(newProduct).ifPresent(createProductStrategy);
                    buildResponse(productRS, newProduct);
                }
                return productRS;
            }

        } else {
            return productRS;
        }
        return productRS;
    }

    private Product buildNewProduct() {
        return productBuilder.createNewProduct();
    }

    private void buildResponse(ProductRS productRS, Product newProduct) {
        productRS.getProductsList().add(mapToDto(newProduct));
    }

    private ProductDto mapToDto(Product product) {
        return ProductDtoMapper.mapTo.apply(product);
    }

    private boolean isNotEnoughInStorage(final List<Product> productsToBeMade) {
        return massPredicate.test(productsToBeMade);
    }


    private void sendMessageMassAbsence() {
        orderProducer.accept("Not enough mass");
    }

    private List<Product> synchronizationProductsToBeMade(final List<CreateProductRQ> createProductRQ) {
        return productSynchronizationConsumer.apply(createProductRQ);
    }
}

package pl.dawydiuk.Foundry.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import pl.dawydiuk.Foundry.builder.ProductBuilder;
import pl.dawydiuk.Foundry.consumer.CreateProductStrategy;
import pl.dawydiuk.Foundry.consumer.ProductSynchronizationConsumer;
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
    private final ProductSynchronizationConsumer productSynchronizationConsumer;
    private final MassPredicate massPredicate;

    public void createProduct(final List<Product> newProductList) {
        final List<Product> productList = Optional.ofNullable(newProductList).orElse(new ArrayList<Product>());
        if(!productList.isEmpty()){
            List<Product> productsToBeMade = synchronizationProductsToBeMade(productList);
            if (isNotEnoughInStorage(productsToBeMade)) {
                sendMessageMassAbsence();
            } else {
                for (int productId = 0; productId < productsToBeMade.size(); productId++) {
                    Product newProduct = productBuilder.createNewProduct();
                    startProduction(newProduct);
                }
            }
        }

    }

    private boolean isNotEnoughInStorage(List<Product> productsToBeMade) {
        return massPredicate.test(productsToBeMade);
    }

    private void startProduction(Product newProduct) {
        createProductStrategy.accept(newProduct);
    }

    private void sendMessageMassAbsence() {
        orderProducer.accept("Not enough mass");
    }

    private List<Product> synchronizationProductsToBeMade(List<Product> productList) {
        return productSynchronizationConsumer.apply(productList);
    }
}

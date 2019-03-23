package pl.dawydiuk.Foundry.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import pl.dawydiuk.Foundry.builder.ProductBuilder;
import pl.dawydiuk.Foundry.consumer.CreateProductStrategy;
import pl.dawydiuk.Foundry.consumer.ProductSynchronizationConsumer;

import static pl.dawydiuk.Foundry.predicate.MassPredicate.isEnoughInStorage;
import static pl.dawydiuk.Foundry.storage.Storage.MASS;


@AllArgsConstructor
@Slf4j
public class ProductProducer {

    private final OrderProducer orderProducer;
    private final ProductBuilder productBuilder;
    private final CreateProductStrategy createProductStrategy;
    private final ProductSynchronizationConsumer productSynchronizationConsumer;

    public void createProduct(final int howManyNewProducts) {
        synchronizationProductsToBeMade(howManyNewProducts);
        if (!isEnoughInStorage().test(MASS)) {
            sendMessageMassAbsence();
        } else {
            for (int productId = 0; productId < howManyNewProducts; productId++) {
                Product newProduct = productBuilder.createNewProduct();
                startProduction(newProduct);
            }
        }

    }

    private void startProduction(Product newProduct) {
        createProductStrategy.accept(newProduct);
    }

    private void sendMessageMassAbsence() {
        orderProducer.accept("Not enough mass");
    }

    private void synchronizationProductsToBeMade(int howManyNewProducts) {
        productSynchronizationConsumer.accept(howManyNewProducts);
    }
}

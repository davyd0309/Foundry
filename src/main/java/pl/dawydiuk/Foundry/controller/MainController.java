package pl.dawydiuk.Foundry.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.Foundry.consumer.ProductSynchronizationConsumer;
import pl.dawydiuk.Foundry.service.ProductProducer;

import static pl.dawydiuk.Foundry.storage.Storage.PRODUCTS_TO_BE_MADE;

/**
 * Created by Judith on 26.12.2018.
 */

@RestController
@AllArgsConstructor
@Slf4j
public class MainController {

    private final ProductProducer productProducer;
    private final ProductSynchronizationConsumer productSynchronizationConsumer;

    @GetMapping(value = "/create/{howManyProducts}")
    public void production(@PathVariable int howManyNewProducts) {
        synchronizationProductsToBeMade(howManyNewProducts);
        productProducer.createProduct(PRODUCTS_TO_BE_MADE);
    }

    private void synchronizationProductsToBeMade(int howManyNewProducts) {
        productSynchronizationConsumer.accept(howManyNewProducts);
    }

}

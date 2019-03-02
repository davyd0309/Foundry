package pl.dawydiuk.Foundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.Foundry.service.ProductProducer;

import static pl.dawydiuk.Foundry.storage.Storage.PRODUCTS_TO_BE_MADE;

/**
 * Created by Judith on 26.12.2018.
 */

@RestController
public class MainController {

    private ProductProducer productProducer;

    @Autowired
    public MainController(ProductProducer productProducer) {
        this.productProducer = productProducer;
    }

    @GetMapping(value = "/start/{howManyProducts}")
    public void production(@PathVariable int howManyNewProducts) {
        PRODUCTS_TO_BE_MADE = PRODUCTS_TO_BE_MADE + howManyNewProducts;
        productProducer.createProduct(PRODUCTS_TO_BE_MADE);
    }

}

package pl.dawydiuk.Foundry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.Foundry.service.ProductProducer;
import pl.dawydiuk.Foundry.service.ProductReducer;

import static pl.dawydiuk.Foundry.storage.Storage.PRODUCTS_TO_BE_MADE;

/**
 * Created by Judith on 26.12.2018.
 */

@RestController
public class MainController {

    private ProductProducer productProducer;
    private ProductReducer productReducer;

    public MainController(ProductProducer productProducer, ProductReducer productReducer) {
        this.productProducer = productProducer;
        this.productReducer = productReducer;
    }

    @GetMapping(value = "/start/{howManyProducts}")
    public void production(@PathVariable int howManyNewProducts) {
        productReducer.addProductToBeMade(howManyNewProducts);
        productProducer.createProduct(PRODUCTS_TO_BE_MADE);
    }

}

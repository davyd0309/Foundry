package pl.dawydiuk.Foundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.Foundry.service.ProductProducer;

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

    @GetMapping(value = "/start/{products}")
    public void production(@PathVariable int howManyProducts) {
        productProducer.createProduct(howManyProducts);
    }

}

package pl.dawydiuk.Foundry.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.Foundry.service.ProductProducer;

import java.util.List;

/**
 * Created by Judith on 26.12.2018.
 */

@RestController
@AllArgsConstructor
@Slf4j
public class MainController {

    private final ProductProducer productProducer;

    @GetMapping(value = "/create")
    public void production(@RequestBody final List<Product> productList) {
        productProducer.createProduct(productList);
    }


}

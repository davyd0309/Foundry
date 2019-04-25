package pl.dawydiuk.Foundry.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.CreateProductRQ;
import models.ProductRS;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.Foundry.service.ProductFascade;

import java.util.List;

/**
 * Created by Judith on 26.12.2018.
 */

@RestController
@AllArgsConstructor
@Slf4j
public class MainController {

    private final ProductFascade productFascade;

    @PostMapping(value = "/products")
    public void createNewProducts(@RequestBody List<CreateProductRQ> createProductRQ) {
        ProductRS productRS = productFascade.createProduct(createProductRQ);
    }

    @PostMapping(value = "/products")
    public void getAllProducts() {
        ProductRS productRS = productFascade.getAllProducts();
    }

}

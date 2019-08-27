package pl.dawydiuk.Foundry.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.ProductRQ;
import models.ProductRS;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.Foundry.service.ProductFascade;

/**
 * Created by Konrad on 26.12.2018.
 */

@RestController
@AllArgsConstructor
@Slf4j
public class MainController {

    private final ProductFascade productFascade;

    @PostMapping(value = "/create")
    public ProductRS createNewProducts(@RequestBody ProductRQ productRQ) {
//        ProductRS productRS = productFascade.createProduct(productRQ);
        return new ProductRS();
    }

    @PostMapping(value = "/products")
    public void getAllProducts() {
        ProductRS productRS = productFascade.getAllProducts();
    }

}

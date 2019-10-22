package pl.dawydiuk.Foundry.builder;

import lombok.extern.slf4j.Slf4j;
import models.Product;
import models.enums.ProductType;

import java.time.LocalDateTime;

/**
 * Created by Konrad on 09.03.2019.
 */



@Slf4j
public class ProductBuilder {

    public Product createNewProduct(ProductType productType) {
        return Product.builder()
                .type(productType)
                .foundryDate(LocalDateTime.now())
                .build();
    }
}

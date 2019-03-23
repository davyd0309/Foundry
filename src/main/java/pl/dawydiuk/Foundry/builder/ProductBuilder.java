package pl.dawydiuk.Foundry.builder;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;

import java.time.LocalDateTime;

/**
 * Created by Judith on 09.03.2019.
 */


@NoArgsConstructor
@Slf4j
public class ProductBuilder {

    public Product createNewProduct() {
        return Product.builder()
                .foundryDate(LocalDateTime.now())
                .build();
    }
}

package pl.dawydiuk.Foundry.builder;

import models.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Judith on 09.03.2019.
 */

@Component
public class ProductBuilder {

    public Product createNewProduct() {
        return Product.builder()
                .foundryDate(LocalDateTime.now())
                .build();
    }
}

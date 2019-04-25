package pl.dawydiuk.Foundry.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;

import java.util.function.Consumer;

/**
 * Created by Judith on 17.03.2019.
 */

@AllArgsConstructor
@Slf4j
public class CreateProductStrategy implements Consumer<Product> {

    private final CreateProductChainBuilder createProductChainBuilder;

    @Override
    public void accept(Product product) {
        for (ProductContexConsumer consumer : createProductChainBuilder.buildChain()) {
            consumer.execute(product);
        }
    }
}

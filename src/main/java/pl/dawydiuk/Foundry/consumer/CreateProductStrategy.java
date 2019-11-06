package pl.dawydiuk.Foundry.consumer;

import lombok.extern.slf4j.Slf4j;
import models.Product;

import java.util.function.Consumer;

/**
 * Created by Konrad on 17.03.2019.
 */

@Slf4j
public class CreateProductStrategy implements Consumer<Product> {

    private final CreateProductChainBuilder createProductChainBuilder;

    public CreateProductStrategy(CreateProductChainBuilder createProductChainBuilder) {
        this.createProductChainBuilder = createProductChainBuilder;
    }

    @Override
    public void accept(Product product) {
        for (ProductContexConsumer consumer : createProductChainBuilder.buildChain()) {
            consumer.execute(product);
        }
    }
}

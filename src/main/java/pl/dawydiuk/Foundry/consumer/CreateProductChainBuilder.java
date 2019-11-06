package pl.dawydiuk.Foundry.consumer;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by Konrad on 17.03.2019.
 */

@Slf4j
public class CreateProductChainBuilder {

    private final ProductContexConsumer createProductConsumer;
    private final ProductContexConsumer productPersistConsumer;
    private final ProductContexConsumer productMassReducerConsumer;

    public CreateProductChainBuilder(ProductContexConsumer createProductConsumer, ProductContexConsumer productPersistConsumer, ProductContexConsumer productMassReducerConsumer) {
        this.createProductConsumer = createProductConsumer;
        this.productPersistConsumer = productPersistConsumer;
        this.productMassReducerConsumer = productMassReducerConsumer;
    }

    public List<ProductContexConsumer> buildChain() {
        return List.of(createProductConsumer, productPersistConsumer, productMassReducerConsumer);
    }
}

package pl.dawydiuk.Foundry.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 17.03.2019.
 */


@AllArgsConstructor
@Slf4j
public class CreateProductChainBuilder {

    private final CreateProductConsumer createProductConsumer;
    private final ProductPersistConsumer productPersistConsumer;
    private final ProductMassReducerConsumer productCountReducerConsumer;

    public List<ProductContexConsumer> buildChain() {
        ArrayList<ProductContexConsumer> productContexConsumers = new ArrayList<>();
        productContexConsumers.add(createProductConsumer);
        productContexConsumers.add(productPersistConsumer);
        productContexConsumers.add(productCountReducerConsumer);
        return productContexConsumers;
    }
}

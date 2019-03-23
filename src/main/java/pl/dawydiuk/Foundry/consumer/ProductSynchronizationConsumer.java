package pl.dawydiuk.Foundry.consumer;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.IntConsumer;

import static pl.dawydiuk.Foundry.storage.Storage.PRODUCTS_TO_BE_MADE;

/**
 * Created by Judith on 21.03.2019.
 */

@NoArgsConstructor
@Slf4j
public class ProductSynchronizationConsumer implements IntConsumer {

    @Override
    public void accept(int howManyNewProducts) {
        PRODUCTS_TO_BE_MADE = PRODUCTS_TO_BE_MADE + howManyNewProducts;
    }
}

package pl.dawydiuk.Foundry.consumer;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;

import static pl.dawydiuk.Foundry.storage.Storage.PRODUCTS_TO_BE_MADE;

/**
 * Created by Judith on 09.03.2019.
 */

@NoArgsConstructor
@Slf4j
public class ProductMassReducerConsumer implements ProductContexConsumer<Product> {

    private void reduceMass(Product newProduct) {
        PRODUCTS_TO_BE_MADE = PRODUCTS_TO_BE_MADE - 1;//to powiino redukowac mase
    }

    @Override
    public void execute(Product newProduct) {
        reduceMass(newProduct);
    }
}

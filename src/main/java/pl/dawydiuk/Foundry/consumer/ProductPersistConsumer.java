package pl.dawydiuk.Foundry.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import pl.dawydiuk.Foundry.repository.ProductDao;

/**
 * Created by Judith on 17.03.2019.
 */

@AllArgsConstructor
@Slf4j
public class ProductPersistConsumer implements ProductContexConsumer<Product>{

    private final ProductDao productDao;

    @Override
    public void execute(Product newProduct) {
        productDao.persist(newProduct);
    }
}

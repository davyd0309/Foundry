package pl.dawydiuk.Foundry.consumer;

import lombok.extern.slf4j.Slf4j;
import models.Product;
import pl.dawydiuk.Foundry.repository.ProductDao;

/**
 * Created by Konrad on 17.03.2019.
 */


@Slf4j
public class ProductPersistConsumer implements ProductContexConsumer<Product>{

    private final ProductDao productDao;

    public ProductPersistConsumer(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void execute(Product newProduct) {
        productDao.persist(newProduct);
    }
}

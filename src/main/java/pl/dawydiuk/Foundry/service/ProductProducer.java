package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.springframework.stereotype.Service;
import pl.dawydiuk.Foundry.builder.ProductBuilder;
import pl.dawydiuk.Foundry.repository.ProductDao;

import static pl.dawydiuk.Foundry.predicate.MassPredicate.isEnoughInStorage;
import static pl.dawydiuk.Foundry.storage.Storage.MASS;


@Service
@Slf4j
public class ProductProducer {

    private final OrderProducer orderProducer;
    private final CreateProductProducer createProductProducer;
    private final ProductBuilder productBuilder;
    private ProductReducer productReducer;
    private ProductDao productDao;

    public ProductProducer(OrderProducer orderProducer, CreateProductProducer createProductProducer, ProductBuilder productBuilder, ProductReducer productReducer, ProductDao productDao) {
        this.orderProducer = orderProducer;
        this.createProductProducer = createProductProducer;
        this.productBuilder = productBuilder;
        this.productReducer = productReducer;
        this.productDao = productDao;
    }

    public void createProduct(final int howManyProducts) {

        if (!isEnoughInStorage().test(MASS)) {
            orderProducer.accept("Not enough mass");
        } else {
            for (int productId = 0; productId < howManyProducts; productId++) {
                Product newProduct = productBuilder.createNewProduct();
                createProductProducer.accept(newProduct);
                productDao.persist(newProduct);
                productReducer.reduceProductToBeMade();
            }
        }

    }


}

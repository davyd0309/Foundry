package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static pl.dawydiuk.Foundry.predicate.MassPredicate.isEnoughInStorage;
import static pl.dawydiuk.Foundry.storage.Storage.MASS;


@Service
@Slf4j
public class ProductProducer {

    private final OrderProducer orderProducer;
    private final CreateProductProducer createProductProducer;

    @Autowired
    public ProductProducer(OrderProducer orderProducer, CreateProductProducer createProductProducer) {
        this.orderProducer = orderProducer;
        this.createProductProducer = createProductProducer;
    }

    public void createProduct(int howManyProducts) {
        for (int i = 0; i < howManyProducts; i++) {
            int productId = 0;
            if (isEnoughInStorage().test(MASS)) {
                createProductProducer.accept(createNewProduct(productId));
                productId++;
            }else {
                orderProducer.accept("Not enough mass");
            }
        }
    }

    private Product createNewProduct(int productId) {
        return Product.builder()
                .id(productId)
                .foundryDate(LocalDateTime.now())
                .build();
    }


}

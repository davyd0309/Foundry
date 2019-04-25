package pl.dawydiuk.Foundry.consumer;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.CreateProductRQ;
import models.Product;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Judith on 21.03.2019.
 */

@NoArgsConstructor
@Slf4j
public class ProductSynchronizationConsumer implements Function<List<CreateProductRQ>, List<Product>> {

    @Override
    public List<Product> apply(List<CreateProductRQ> createProductRQ) {
        //uderza do bazy i sprawdza co zostalo z zapasow do zrobienia i dodaje to co trzeba zrobić
        //mapuje na liste produktow

        return List.of(Product.defaultInstance(), Product.defaultInstance(), Product.defaultInstance());
    }
}

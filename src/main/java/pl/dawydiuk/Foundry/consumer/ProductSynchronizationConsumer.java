package pl.dawydiuk.Foundry.consumer;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Judith on 21.03.2019.
 */

@NoArgsConstructor
@Slf4j
public class ProductSynchronizationConsumer implements Function<List<Product>,List<Product>> {

    @Override
    public List<Product> apply(List<Product> productList) {
        //uderza do bazy i sprawdza co zostalo z zapasow do zrobienia i dodaje to co trzeba zrobić
        return null;
    }
}

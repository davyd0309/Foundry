package pl.dawydiuk.Foundry.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import models.ProductRQ;
import pl.dawydiuk.Foundry.repository.ProductDao;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Konrad on 21.03.2019.
 */

@AllArgsConstructor
@Slf4j
public class ProductSynchronizationConsumer implements Function<List<ProductRQ>, List<Product>> {

    private final ProductDao productDao;

    @Override
    public List<Product> apply(List<ProductRQ> productRQ) {
        //uderza do bazy i sprawdza co zostalo z zapasow do zrobienia i dodaje to co trzeba zrobić
        //mapuje na liste produktow

        List<Product> allProduct = productDao.getAll();
        return Collections.emptyList();
    }
}

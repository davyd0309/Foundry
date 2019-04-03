package pl.dawydiuk.Foundry.predicate;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Judith on 27.12.2018.
 */

@NoArgsConstructor
@Slf4j
public class MassPredicate implements Predicate<List<Product>>{


    @Override
    public boolean test(List<Product> productList) {
        return true;//sprawdza w bazie czy jest wystarczajaca ilosc masy do zrobienia produktow
    }
}

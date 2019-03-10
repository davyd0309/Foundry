package pl.dawydiuk.Foundry.service;

import org.springframework.stereotype.Service;

import static pl.dawydiuk.Foundry.storage.Storage.PRODUCTS_TO_BE_MADE;

/**
 * Created by Judith on 09.03.2019.
 */
@Service
public class ProductReducer {

    public void reduceProductToBeMade(){
        PRODUCTS_TO_BE_MADE = PRODUCTS_TO_BE_MADE - 1;
    }

    public void addProductToBeMade(final int howManyProducts){
        PRODUCTS_TO_BE_MADE = PRODUCTS_TO_BE_MADE + howManyProducts;
    }

}

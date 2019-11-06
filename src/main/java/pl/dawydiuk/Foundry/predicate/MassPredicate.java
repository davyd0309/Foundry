package pl.dawydiuk.Foundry.predicate;


import lombok.extern.slf4j.Slf4j;
import models.AddProduct;
import models.Mass;
import models.ProductCreateRQ;
import models.enums.ProductType;
import pl.dawydiuk.Foundry.repository.MassDao;

import java.util.function.Predicate;

/**
 * Created by Konrad on 27.12.2018.
 */

@Slf4j
public class MassPredicate implements Predicate<ProductCreateRQ>{

    private final MassDao massDao;

    public MassPredicate(MassDao massDao) {
        this.massDao = massDao;
    }

    @Override
    public boolean test(final ProductCreateRQ productCreateRQ) {
        return calculatingTheQuantityInTheWarehouse()>=calculationOfDemand(productCreateRQ);
    }

    private double calculatingTheQuantityInTheWarehouse() {
        Mass allMass = massDao.getAllMass();
        return allMass.getWeight();
    }

    private double calculationOfDemand(ProductCreateRQ productCreateRQ) {
        return productCreateRQ.getProductList().stream()
                .map(AddProduct::getType)
                .mapToDouble(ProductType::getAmountOfMass)
                .sum();
    }
}

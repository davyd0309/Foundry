package pl.dawydiuk.Foundry.predicate;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Mass;
import models.ProductRQ;
import models.enums.ProductType;
import pl.dawydiuk.Foundry.repository.MassDao;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Konrad on 27.12.2018.
 */

@AllArgsConstructor
@Slf4j
public class MassPredicate implements Predicate<List<ProductRQ>>{

    private final MassDao massDao;


    @Override
    public boolean test(final List<ProductRQ> productRQS) {
        return calculatingTheQuantityInTheWarehouse()>=calculationOfDemand(productRQS);
    }

    private double calculatingTheQuantityInTheWarehouse() {
        Mass allMass = massDao.getAllMass();
        return allMass.getWeight();
    }

    private double calculationOfDemand(final List<ProductRQ> productRQS) {
        return productRQS.stream()
                .map(ProductRQ::getType)
                .mapToDouble(ProductType::getAmountOfMass)
                .sum();
    }
}

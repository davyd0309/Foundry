package pl.dawydiuk.Foundry.predicate;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.CreateProductRQ;
import models.Mass;
import models.enums.ProductType;
import pl.dawydiuk.Foundry.repository.MassDao;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Konrad on 27.12.2018.
 */

@AllArgsConstructor
@Slf4j
public class MassPredicate implements Predicate<List<CreateProductRQ>>{

    private final MassDao massDao;


    @Override
    public boolean test(final List<CreateProductRQ> createProductRQS) {
        return calculatingTheQuantityInTheWarehouse()>=calculationOfDemand(createProductRQS);
    }

    private double calculatingTheQuantityInTheWarehouse() {
        Mass allMass = massDao.getAllMass();
        return allMass.getWeight();
    }

    private double calculationOfDemand(final List<CreateProductRQ> createProductRQS) {
        return createProductRQS.stream()
                .map(CreateProductRQ::getType)
                .mapToDouble(ProductType::getAmountOfMass)
                .sum();
    }
}

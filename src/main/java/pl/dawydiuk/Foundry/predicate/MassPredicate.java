package pl.dawydiuk.Foundry.predicate;

import pl.dawydiuk.ConversionOfMass.model.Mass;

import java.util.function.Predicate;

/**
 * Created by Judith on 27.12.2018.
 */

public class MassPredicate {

    public static Predicate<Mass> isEnoughInStorage() {
        return mass -> mass.getWeight() > 50;
    }


}

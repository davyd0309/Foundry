package pl.dawydiuk.Foundry.predicate;


import lombok.extern.slf4j.Slf4j;
import models.Mass;

import java.util.function.Predicate;

/**
 * Created by Judith on 27.12.2018.
 */

@Slf4j
public class MassPredicate {

    public static Predicate<Mass> isEnoughInStorage() {
        return mass -> mass.getWeight() > 50;
    }

}

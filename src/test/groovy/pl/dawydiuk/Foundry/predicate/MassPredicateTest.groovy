package pl.dawydiuk.Foundry.predicate

import models.CreateProductRQ
import models.Mass
import models.enums.ProductType
import pl.dawydiuk.Foundry.repository.MassDao
import spock.lang.Specification

/**
 * Created by Konrad on 26.03.2019.
 */
class MassPredicateTest extends Specification {

    private MassDao massDao
    private MassPredicate massPredicate

    void setup() {
        massDao = Stub(MassDao.class)
        massPredicate = new MassPredicate(massDao)
    }

    def "Test should return true if there is enough mass"() {
        given:
        List<CreateProductRQ> productsToBeMade = prepareCreateProductRQList()
        massDao.getAllMass() >> Mass.builder().weight(515.88).build()

        when:
        boolean status = massPredicate.test(productsToBeMade)

        then:
        status
    }

    def "Test should return false if there is not enough mass"() {
        given:
        List<CreateProductRQ> productsToBeMade = prepareCreateProductRQList()
        massDao.getAllMass() >> Mass.builder().weight(10).build()

        when:
        boolean status = massPredicate.test(productsToBeMade)

        then:
        !status
    }

    def "Test should return true if the values are the same"() {
        given:
        List<CreateProductRQ> productsToBeMade = prepareCreateProductRQList()
        massDao.getAllMass() >> Mass.builder().weight(498).build()

        when:
        boolean status = massPredicate.test(productsToBeMade)

        then:
        status
    }

    private List<CreateProductRQ> prepareCreateProductRQList(){
        return List.of(CreateProductRQ.builder().type(ProductType.COMPACT_BOWL).build(),
                CreateProductRQ.builder().type(ProductType.URINAL).build(),
                CreateProductRQ.builder().type(ProductType.COMPACT_BOWL).build())
    }

}

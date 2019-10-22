package pl.dawydiuk.Foundry.builder

import models.Product
import models.enums.ProductType
import spock.lang.Specification

/**
 * Created by Konrad on 26.03.2019.
 */
class ProductBuilderTest extends Specification {

    private ProductBuilder productBuilder

    void setup(){
        productBuilder = new ProductBuilder()
    }

    //TODO add rest field
    //TODO use clock class for check date
    def "CreateNewProduct should create new product"(){
        given:
        def washbasin = ProductType.WASHBASIN
        Product actualProduct = productBuilder.createNewProduct(washbasin)

        expect:
        actualProduct.type == washbasin
    }

}

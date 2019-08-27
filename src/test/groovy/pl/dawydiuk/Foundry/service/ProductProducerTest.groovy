package pl.dawydiuk.Foundry.service

import models.ProductRQ
import models.Product
import models.ProductRS
import models.enums.ProductType
import pl.dawydiuk.Foundry.builder.ProductBuilder
import pl.dawydiuk.Foundry.consumer.CreateProductStrategy
import pl.dawydiuk.Foundry.consumer.ProductSynchronizationConsumer
import pl.dawydiuk.Foundry.predicate.MassPredicate
import spock.lang.Specification

import java.time.LocalDateTime

import static org.mockito.ArgumentMatchers.any

/**
 * Created by Konrad on 24.03.2019.
 */
class ProductProducerTest extends Specification {

    private OrderProducer orderProducer
    private ProductBuilder productBuilder
    private CreateProductStrategy createProductStrategy
    private ProductSynchronizationConsumer productSynchronizationConsumer
    private MassPredicate massPredicate

    private ProductProducer productProducer

    void setup() {
        orderProducer = Mock()
        productBuilder = Mock()
        createProductStrategy = Mock()
        productSynchronizationConsumer = Mock()
        massPredicate = Mock()
        productProducer = new ProductProducer(orderProducer, productBuilder, createProductStrategy, massPredicate, productSynchronizationConsumer)
    }

    def "createProduct should invoke mass test if rq is not empty"() {
        given:
        List<ProductRQ> createProductRQ = List.of(ProductRQ.builder()
                .type(ProductType.COMPACT_BOWL)
                .number(10)
                .build())
        productBuilder.createNewProduct(ProductType.COMPACT_BOWL) >> Product.builder()
                .type(ProductType.COMPACT_BOWL)
                .foundryDate(LocalDateTime.now())
                .build()
        when:
        productProducer.createProduct(createProductRQ)

        then:
        1 * massPredicate.test(createProductRQ)
    }

    def "createProduct should NOT invoke mass test if rq is empty"() {
        given:
        List<ProductRQ> createProductRQ = List.of()
        productBuilder.createNewProduct(ProductType.COMPACT_BOWL) >> Product.builder()
                .type(ProductType.COMPACT_BOWL)
                .foundryDate(LocalDateTime.now())
                .build()
        when:
        productProducer.createProduct(createProductRQ)

        then:
        0 * massPredicate.test(createProductRQ)
    }

    def "createProduct should invoke sendMessageMassAbsence if is not enough mass in storage"() {
        given:
        List<ProductRQ> createProductRQ = List.of(ProductRQ.builder()
                .type(ProductType.COMPACT_BOWL)
                .number(10)
                .build())
        productBuilder.createNewProduct(ProductType.COMPACT_BOWL) >> Product.builder()
                .type(ProductType.COMPACT_BOWL)
                .foundryDate(LocalDateTime.now())
                .build()
        massPredicate.test(createProductRQ) >> false

        when:
        productProducer.createProduct(createProductRQ)

        then:
        1 * orderProducer.accept(_)
    }

    def "createProduct should return empty productRS if is not enough mass in storage"() {
        given:
        ProductRS expectedProductRS = new ProductRS()
        List<ProductRQ> createProductRQ = List.of(ProductRQ.builder()
                .type(ProductType.COMPACT_BOWL)
                .number(10)
                .build())
        productBuilder.createNewProduct(ProductType.COMPACT_BOWL) >> Product.builder()
                .type(ProductType.COMPACT_BOWL)
                .foundryDate(LocalDateTime.now())
                .build()
        massPredicate.test(createProductRQ) >> false

        when:
        ProductRS actualProductRS = productProducer.createProduct(createProductRQ)

        then:
        actualProductRS == expectedProductRS
    }

    def "createProduct should NOT invoke sendMessageMassAbsence if is enough mass in storage"() {
        given:
        List<ProductRQ> createProductRQ = List.of(ProductRQ.builder()
                .type(ProductType.COMPACT_BOWL)
                .number(10)
                .build())
        productBuilder.createNewProduct(ProductType.COMPACT_BOWL) >> Product.builder()
                .type(ProductType.COMPACT_BOWL)
                .foundryDate(LocalDateTime.now())
                .build()
        massPredicate.test(createProductRQ) >> true

        when:
        productProducer.createProduct(createProductRQ)

        then:
        0 * orderProducer.accept(_)

    }

    def "createProduct should invoke production if is enough mass in storage"() {
        given:
        List<ProductRQ> createProductRQ = List.of(ProductRQ.builder()
                .type(ProductType.COMPACT_BOWL)
                .number(10)
                .build())
        productBuilder.createNewProduct(ProductType.COMPACT_BOWL) >> Product.builder()
                .type(ProductType.COMPACT_BOWL)
                .foundryDate(LocalDateTime.now())
                .build()
        massPredicate.test(createProductRQ) >> true
        when:
        productProducer.createProduct(createProductRQ)
        then:
        10 * productBuilder.createNewProduct(ProductType.COMPACT_BOWL)
        10 * createProductStrategy.accept(any(Product.class))
    }
//
//    def "createProduct should NOT invoke production if is not enough mass in storage"() {
//        given:
//
//        when:
//
//        then:
//
//    }
//
//    def "createProduct should productRS if everything is ok"() {
//        given:
//
//        when:
//
//        then:
//
//    }
//    def "calculationOfDemand should calculate correct"() {
//        given:
//
//        when:
//
//        then:
//
//    }

    //    def "buildResponse should add DTO to list"() {
//        given:
//
//        when:
//
//        then:
//
//    }




}

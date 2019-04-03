package pl.dawydiuk.Foundry.service

import models.Product
import pl.dawydiuk.Foundry.builder.ProductBuilder
import pl.dawydiuk.Foundry.consumer.CreateProductStrategy
import pl.dawydiuk.Foundry.consumer.ProductSynchronizationConsumer
import pl.dawydiuk.Foundry.predicate.MassPredicate
import spock.lang.Specification

/**
 * Created by Judith on 24.03.2019.
 */
class ProductProducerTest extends Specification {

    private OrderProducer orderProducer;
    private ProductBuilder productBuilder;
    private CreateProductStrategy createProductStrategy;
    private ProductSynchronizationConsumer productSynchronizationConsumer;
    private MassPredicate massPredicate;

    private ProductProducer productProducer;

    void setup() {
        orderProducer = Mock()
        productBuilder = Mock()
        createProductStrategy = Mock()
        productSynchronizationConsumer = Mock()
        massPredicate = Mock()
        productProducer = new ProductProducer(orderProducer, productBuilder,
                createProductStrategy, productSynchronizationConsumer, massPredicate)
    }

    def "CreateProduct_should invoke synchronization only once with correct parameter"() {
        given:
        List<Product> productList = Arrays.asList(Product.defaultInstance(), Product.defaultInstance(), Product.defaultInstance())
        when:
        productProducer.createProduct(productList)
        then:
        1 * productSynchronizationConsumer.apply(productList) >> productList

    }

    def "CreateProduct_should invoke send message only once if is not enough mass"() {
        given:
        massPredicate.test(_) >> true
        when:
        productProducer.createProduct(_)
        then:
        1 * orderProducer.accept(_)

    }

    def "CreateProduct_should NOT invoke send message if is enough mass"() {
        given:
        productSynchronizationConsumer.apply(_) >> Arrays.asList(Product.defaultInstance(), Product.defaultInstance(), Product.defaultInstance(),Product.defaultInstance(), Product.defaultInstance(), Product.defaultInstance())
        massPredicate.test(_) >> false
        when:
        productProducer.createProduct(_)
        then:
        0 * orderProducer.accept(_)
    }

    def "CreateProduct_should create as many products as there is to be made"() {
        given:
        List<Product> productList = Arrays.asList(Product.defaultInstance(), Product.defaultInstance(), Product.defaultInstance())
        productSynchronizationConsumer.apply(productList) >> Arrays.asList(Product.defaultInstance(), Product.defaultInstance(), Product.defaultInstance(),Product.defaultInstance(), Product.defaultInstance(), Product.defaultInstance())
        Product createdProduct = Product.defaultInstance()
        when:
        productProducer.createProduct(productList)
        then:
        6 * productBuilder.createNewProduct() >> createdProduct
        6 * createProductStrategy.accept(createdProduct)
    }

    def "CreateProduct_should create 0 products if products to be made equals 0"() {
        given:
        List<Product> productList = Collections.emptyList()
        productSynchronizationConsumer.apply(productList) >> Collections.emptyList()
        Product createdProduct = Product.defaultInstance()
        when:
        productProducer.createProduct(productList)
        then:
        0 * productBuilder.createNewProduct() >> createdProduct
        0 * createProductStrategy.accept(createdProduct)
    }

    def "CreateProduct_should create 0 if products list equals null"() {
        given:
        List<Product> productList = null
        Product createdProduct = Product.defaultInstance()
        when:
        productProducer.createProduct(productList)
        then:
        0 * productBuilder.createNewProduct() >> createdProduct
        0 * createProductStrategy.accept(createdProduct)
    }

}

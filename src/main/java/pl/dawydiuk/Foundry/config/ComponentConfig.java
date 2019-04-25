package pl.dawydiuk.Foundry.config;

import models.Product;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import pl.dawydiuk.Foundry.builder.ProductBuilder;
import pl.dawydiuk.Foundry.consumer.*;
import pl.dawydiuk.Foundry.predicate.MassPredicate;
import pl.dawydiuk.Foundry.repository.MassDao;
import pl.dawydiuk.Foundry.repository.ProductDao;
import pl.dawydiuk.Foundry.service.*;

/**
 * Created by Judith on 23.03.2019.
 */

@Configuration
public class ComponentConfig {

    @Bean
    public ProductBuilder productBuilder() {
        return new ProductBuilder();
    }

    @Bean
    public CreateProductChainBuilder createProductChainBuilder(CreateProductConsumer createProductConsumer,
                                                               ProductMassReducerConsumer productCountReducerConsumer, ProductPersistConsumer productPersistConsumer) {
        return new CreateProductChainBuilder(createProductConsumer, productPersistConsumer, productCountReducerConsumer);

    }

    @Bean
    public CreateProductConsumer createProductConsumer(KafkaTemplate<String, Product> kafkaTemplateProduct) {
        return new CreateProductConsumer(kafkaTemplateProduct);
    }

    @Bean
    public CreateProductStrategy createProductStrategy(CreateProductChainBuilder createProductChainBuilder) {
        return new CreateProductStrategy(createProductChainBuilder);
    }

    @Bean
    public ProductMassReducerConsumer productMassReducerConsumer() {
        return new ProductMassReducerConsumer();
    }

    @Bean
    public ProductPersistConsumer productPersistConsumer(ProductDao productDao) {
        return new ProductPersistConsumer(productDao);
    }

    @Bean
    public ProductSynchronizationConsumer productSynchronizationConsumer() {
        return new ProductSynchronizationConsumer();
    }

    @Bean
    public MassDao massDao(SessionFactory sessionFactory) {
        return new MassDao(sessionFactory);
    }

    @Bean
    public ProductDao productDao(SessionFactory sessionFactory) {
        return new ProductDao(sessionFactory);
    }

    @Bean
    public MassConsumer massConsumer() {
        return new MassConsumer();
    }

    @Bean
    public OrderProducer orderProducer(KafkaTemplate<String, String> kafkaTemplateString) {
        return new OrderProducer(kafkaTemplateString);
    }

    @Bean
    public MassPredicate massPredicate() {
        return new MassPredicate();
    }

    @Bean
    public ProductSearcher productSearcher(ProductDao productDao){
        return new ProductSearcher(productDao);
    }

    @Bean
    public ProductFascade productFascade(ProductProducer productProducer,ProductSearcher productSearcher){
        return new ProductFacadeImpl(productProducer,productSearcher);
    }

    @Bean
    public ProductProducer productProducer(OrderProducer orderProducer,
                                           ProductBuilder productBuilder,
                                           CreateProductStrategy createProductStrategy,
                                           ProductSynchronizationConsumer productSynchronizationConsumer,
                                           MassPredicate massPredicate) {
        return new ProductProducer(orderProducer, productBuilder, createProductStrategy, massPredicate, productSynchronizationConsumer);
    }

}

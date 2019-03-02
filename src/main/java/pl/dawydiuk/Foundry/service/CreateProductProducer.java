package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Created by Judith on 02.02.2019.
 */
@Component
@Slf4j
public class CreateProductProducer implements Consumer<Product> {

    private final KafkaTemplate<String, Product> kafkaTemplateProduct;

    @Value("${app.topic.foundry-product-produced}")
    private String topic;

    public CreateProductProducer(KafkaTemplate<String, Product> kafkaTemplateProduct) {
        this.kafkaTemplateProduct = kafkaTemplateProduct;
    }

    @Override
    public void accept(Product product) {
        kafkaTemplateProduct.send(topic, product);
        log.info("Sending product o id='{}' to topic='{}'", product.getId(),topic);
        reducingTheWeightNeededToMakeOneProduct();
    }

    private void reducingTheWeightNeededToMakeOneProduct() {
        //redukcja masy
    }


}

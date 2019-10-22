package pl.dawydiuk.Foundry.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Created by Konrad on 02.02.2019.
 */

@RequiredArgsConstructor
@Slf4j
public class CreateProductConsumer implements ProductContexConsumer<Product> {

    private final KafkaTemplate<String, Product> kafkaTemplateProduct;

    @Value("${app.topic.foundry-product-produced}")
    private String topic;

    @Override
    public void execute(Product newProduct) {
        kafkaTemplateProduct.send(topic, newProduct);
        log.info("Sending product o id='{}' to topic='{}'", newProduct.getId(),topic);
    }


}

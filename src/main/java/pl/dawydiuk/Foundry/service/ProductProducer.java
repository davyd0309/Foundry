package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.dawydiuk.Foundry.model.Product;

import java.time.LocalDateTime;

import static pl.dawydiuk.Foundry.predicate.MassPredicate.isEnoughInStorage;
import static pl.dawydiuk.Foundry.storage.Storage.mass;

@Service
@Slf4j
public class ProductProducer {

    private final OrderProducer orderProducer;
    private final KafkaTemplate<String, Product> kafkaTemplateProduct;

    @Value("${app.topic.foundry-product-produced}")
    private String topic;

    @Autowired
    public ProductProducer(OrderProducer orderProducer, KafkaTemplate<String, Product> kafkaTemplateProduct) {
        this.orderProducer = orderProducer;
        this.kafkaTemplateProduct = kafkaTemplateProduct;
    }

    public void sendProduct() {
        if (isEnoughInStorage().test(mass)) {
            log.info("sending product to topic='{}'", topic);
            Product product = new Product();
            product.setId(1);
            product.setFoundryDate(LocalDateTime.now());
            kafkaTemplateProduct.send(topic, product);
            mass.setWeight(mass.getWeight() - 25);
        } else {
            orderProducer.sendOrder();
        }
    }

}

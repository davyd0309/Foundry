package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class OrderProducer implements Consumer<String> {


    private final KafkaTemplate<String, String> kafkaTemplateString;

    @Value("${app.topic.orders-from-foundry}")
    private String topic;

    @Autowired
    public OrderProducer(KafkaTemplate<String, String> kafkaTemplateString) {
        this.kafkaTemplateString = kafkaTemplateString;
    }


    @Override
    public void accept(String information) {
        log.info("Send order to topic='{}' info='{}'", topic, information);
        kafkaTemplateString.send(topic, information);
    }
}

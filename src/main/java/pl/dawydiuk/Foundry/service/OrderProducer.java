package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProducer {


    private final KafkaTemplate<String, String> kafkaTemplateString;

    @Value("${app.topic.orders-from-foundry}")
    private String topic;

    @Autowired
    public OrderProducer(KafkaTemplate<String, String> kafkaTemplateString) {
        this.kafkaTemplateString = kafkaTemplateString;
    }


    public void sendOrder() {
        log.info("not enough mass send order to topic='{}'", topic);
        kafkaTemplateString.send(topic, "Mass please");
    }

}

package pl.dawydiuk.Foundry.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Slf4j
public class OrderProducer implements Consumer<String> {

    private final KafkaTemplate<String, String> kafkaTemplateString;

    @Value("${app.topic.orders-from-foundry}")
    private String topic;

    @Override
    public void accept(final String information) {
        log.info("Send order to topic='{}' info='{}'", topic, information);
        kafkaTemplateString.send(topic, information);
    }
}

package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.function.Consumer;

import static java.util.Optional.ofNullable;


@Slf4j
public class OrderProducer implements Consumer<Double> {

    private final KafkaTemplate<String, Double> kafkaTemplateString;

    public OrderProducer(KafkaTemplate<String, Double> kafkaTemplateString) {
        this.kafkaTemplateString = kafkaTemplateString;
    }

    @Value("${app.topic.orders-from-foundry}")
    private String topic;


    @Override
    public void accept(final Double calculationOfDemand) {
        ofNullable(calculationOfDemand)
                .ifPresent(sendInformation(calculationOfDemand));
    }

    private Consumer<Double> sendInformation(Double calculationOfDemand) {
        return cal -> {
//            log.info("Send order to topic='{}' info='{}'", topic, calculationOfDemand);
            kafkaTemplateString.send(topic, calculationOfDemand);
        };
    }
}

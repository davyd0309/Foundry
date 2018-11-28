package pl.dawydiuk.Foundry.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Producer {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.foundry}")
    private String topic;

    public void send(String message) {
        log.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }

}

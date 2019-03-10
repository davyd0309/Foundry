package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import models.Mass;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MassConsumer {

    private static final String MASS_FROM_CONVERSION_OF_MASS_TOPIC = "${app.topic.mass-to-foundry}";

    @KafkaListener(topics = MASS_FROM_CONVERSION_OF_MASS_TOPIC)
    public void getMass(@Payload Mass mass) {
        log.info("Received mass from topic='{}' massId ='{}'", MASS_FROM_CONVERSION_OF_MASS_TOPIC, mass.getId());
        //zapis masy ktora przychodzi do bazy
    }


}

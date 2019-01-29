package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import pl.dawydiuk.ConversionOfMass.model.Mass;
import pl.dawydiuk.Foundry.storage.Storage;


@Service
@Slf4j
public class MassConsumer {

    @KafkaListener(topics = "${app.topic.mass-to-foundry}")
    public void getMass(@Payload Mass mass) {
        log.info("received mass from topic mass ='{}'", mass.getId());
        Storage.mass = mass;
    }

}

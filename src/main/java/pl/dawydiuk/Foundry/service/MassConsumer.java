package pl.dawydiuk.Foundry.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Mass;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;


@NoArgsConstructor
@Slf4j
public class MassConsumer {

    private static final String MASS_FROM_CONVERSION_OF_MASS_TOPIC = "${app.topic.mass-to-foundry}";

    @KafkaListener(topics = MASS_FROM_CONVERSION_OF_MASS_TOPIC, autoStartup = "${kafka.listen:true}")
    public void getMass(@Payload Mass mass) {
//        log.info("Received mass from topic='{}' massId ='{}'", MASS_FROM_CONVERSION_OF_MASS_TOPIC, mass.getId());
        //zapis masy ktora przychodzi do bazy
    }


}

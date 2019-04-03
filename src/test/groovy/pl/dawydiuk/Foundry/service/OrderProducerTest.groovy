package pl.dawydiuk.Foundry.service

import org.springframework.kafka.core.KafkaTemplate
import spock.lang.Specification

/**
 * Created by Judith on 26.03.2019.
 */
class OrderProducerTest extends Specification {

    private OrderProducer orderProducer;
    private KafkaTemplate<String, String> kafkaTemplateString

    void setup() {
        kafkaTemplateString = Mock()
        orderProducer = new OrderProducer(kafkaTemplateString)
    }

    def "Accept_should send correct information only once"() {
        given:
        String expectedInformation = "Some information"
        when:
        orderProducer.accept(expectedInformation)
        then:
        1 * kafkaTemplateString.send(_,expectedInformation)
    }

    def "Accept_should not send if information equals null "() {

    }
}

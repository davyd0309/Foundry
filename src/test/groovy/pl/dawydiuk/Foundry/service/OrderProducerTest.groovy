package pl.dawydiuk.Foundry.service

import org.springframework.kafka.core.KafkaTemplate
import spock.lang.Specification

/**
 * Created by Konrad on 26.03.2019.
 */
class OrderProducerTest extends Specification {

    private OrderProducer orderProducer
    private KafkaTemplate<String, String> kafkaTemplateString

    void setup() {
        kafkaTemplateString = Mock()
        orderProducer = new OrderProducer(kafkaTemplateString)
    }

    def "Accept should send correct information only once"() {
        given:
        Double expectedInformation = 545

        when:
        orderProducer.accept(expectedInformation)

        then:
        1 * kafkaTemplateString.send(_,expectedInformation)
    }

    def "Accept should not send correct information"() {
        given:
        Double expectedInformation = null

        when:
        orderProducer.accept(expectedInformation)

        then:
        0 * kafkaTemplateString.send(_,expectedInformation)
    }

}

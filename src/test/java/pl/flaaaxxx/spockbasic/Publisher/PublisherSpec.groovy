package pl.flaaaxxx.spockbasic.Publisher

import spock.lang.Specification

class PublisherSpec extends Specification {

    Publisher publisher = new Publisher()

    // mockowanie to czynność opisywania (obowiązkowych) interakcji
    // między przedmiotem specyfikacji a jego współpracownikami
    def subscriber = Mock(Subscriber)
    Subscriber subscriber2 = Mock()

    def setup() {
        publisher.subscribers << subscriber // << is a Groovy shorthand for List.add()
        publisher.subscribers.add(subscriber2);
    }

    def "should send messages to all subscribers"() {

        when:
        publisher.send(text)

        then:
        1 * subscriber.receive(text)
        1 * subscriber2.receive(text)
        println(text)

        where:
        text << ["hello"]


    }

}

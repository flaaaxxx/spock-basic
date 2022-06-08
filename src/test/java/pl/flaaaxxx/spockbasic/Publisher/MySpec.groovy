package pl.flaaaxxx.spockbasic.Publisher

import spock.lang.Specification

class MySpec extends Specification {

    Publisher publisher = new Publisher();

//    Subscriber subscriber = Mock()
    Subscriber subscriber = Mock()
    Subscriber subscriber2 = Mock()

    Subscriber subscriber3 = Stub()

    def setup() {
        publisher.subscribers << subscriber // << is a Groovy shorthand for List.add()
        publisher.subscribers.add(subscriber2);
        publisher.subscribers.add(subscriber3);
    }

    def "stub check how its works"() {
        when:
        publisher.send("hello")
        subscriber3.receive2("hello") >> "hello"

        then:
        subscriber3.receive2("hello") == "hello"
    }

    def "ssad"() {

        when:
        publisher.send("hello")

        then:
        1 * subscriber.receive("hello")

        when:
        publisher.send("goodbye")

        then:
        1 * subscriber.receive("goodbye")
//        then:
//        interaction {
//            def message = "hello"
//            1 * subscriber.receive(message)
//        }
    }
}

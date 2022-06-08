package pl.flaaaxxx.spockbasic.Publisher;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    List<Subscriber> subscribers = new ArrayList<>();
    int messageCount = 0;

    void send(String message) {
        subscribers.forEach(x -> x.receive(message));
        subscribers.forEach(x -> x.receive2(message));
        messageCount++;
    }

}

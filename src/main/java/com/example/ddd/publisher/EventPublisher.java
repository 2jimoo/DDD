package com.example.ddd.publisher;

import com.example.ddd.model.event.Event;

public interface EventPublisher<E extends Event> {
    void publish(E event);
}

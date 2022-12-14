

package com.example.ddd.domain.service.publisher;

import com.example.ddd.domain.model.event.Event;

public interface EventPublisher<E extends Event> {
    void publish(E event);
}
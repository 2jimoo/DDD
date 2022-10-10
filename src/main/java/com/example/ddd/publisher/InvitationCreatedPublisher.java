package com.example.ddd.publisher;

import com.example.ddd.model.event.InvitationCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class InvitationCreatedPublisher implements EventPublisher<InvitationCreatedEvent> {
    @Override
    public void publish(InvitationCreatedEvent event) {
    }
}

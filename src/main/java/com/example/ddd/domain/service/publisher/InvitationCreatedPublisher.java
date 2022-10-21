package com.example.ddd.domain.service.publisher;

import com.example.ddd.domain.model.event.InvitationCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class InvitationCreatedPublisher implements EventPublisher<InvitationCreatedEvent> {
    @Override
    public void publish(InvitationCreatedEvent event) {
    }
}

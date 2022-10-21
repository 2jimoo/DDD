package com.example.ddd.domain.service.publisher;

import com.example.ddd.domain.model.event.InvitationRespondedEvent;
import org.springframework.stereotype.Component;

@Component
public class InvitationRespondedPublisher implements EventPublisher<InvitationRespondedEvent> {
    @Override
    public void publish(InvitationRespondedEvent event) {

    }
}

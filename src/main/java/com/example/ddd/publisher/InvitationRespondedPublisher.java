package com.example.ddd.publisher;

import com.example.ddd.model.event.InvitationRespondedEvent;
import org.springframework.stereotype.Component;

@Component
public class InvitationRespondedPublisher implements EventPublisher<InvitationRespondedEvent> {
    @Override
    public void publish(InvitationRespondedEvent event) {

    }
}

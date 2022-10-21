package com.example.ddd.domain.model.event;

import com.example.ddd.domain.model.Invitation;

public record InvitationCreatedEvent(Invitation invitation) implements Event {
}

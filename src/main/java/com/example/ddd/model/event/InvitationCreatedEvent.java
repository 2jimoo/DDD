package com.example.ddd.model.event;

import com.example.ddd.model.Invitation;

public record InvitationCreatedEvent(Invitation invitation) implements Event {
}

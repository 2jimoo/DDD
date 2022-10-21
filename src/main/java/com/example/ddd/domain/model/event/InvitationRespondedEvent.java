package com.example.ddd.domain.model.event;

import com.example.ddd.domain.model.Invitation;

public record InvitationRespondedEvent(Invitation invitationBeforeRespond,
                                       Invitation invitationAfterRespond) implements Event {
}

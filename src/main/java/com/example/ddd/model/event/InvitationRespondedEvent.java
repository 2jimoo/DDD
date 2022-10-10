package com.example.ddd.model.event;

import com.example.ddd.model.Invitation;

public record InvitationRespondedEvent(Invitation invitationBeforeRespond,
                                       Invitation invitationAfterRespond) implements Event {
}

package com.example.ddd.webapp.out;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import org.springframework.stereotype.Component;

@Component
public class InvitationSender implements SendInvitationPort {
    @Override
    public Guid send(Invitation invitation) {
        return invitation.getId();
    }
}

package com.example.ddd.webapp.out;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;

public interface SendInvitationPort {
    Guid send(Invitation invitation);
}

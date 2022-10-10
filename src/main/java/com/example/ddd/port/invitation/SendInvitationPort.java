package com.example.ddd.port.invitation;

import com.example.ddd.model.Guid;
import com.example.ddd.model.Invitation;

public interface SendInvitationPort {
    Guid send(Invitation invitation);
}

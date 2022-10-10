package com.example.ddd.port.invitation;

import com.example.ddd.model.Invitation;

public interface PersistInvitationPort {
    Invitation persist(Invitation invitation);
}

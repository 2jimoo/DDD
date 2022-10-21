package com.example.ddd.domain.port.out.invitation;

import com.example.ddd.domain.model.Invitation;

public interface PersistInvitationPort {
    Invitation persist(Invitation invitation);
}

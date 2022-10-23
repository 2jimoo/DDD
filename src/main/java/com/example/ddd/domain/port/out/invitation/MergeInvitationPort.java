package com.example.ddd.domain.port.out.invitation;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;

import java.time.Instant;

public interface MergeInvitationPort {
    Invitation merge(Invitation invitation, Instant requestedAt, Guid requestedBy);
}

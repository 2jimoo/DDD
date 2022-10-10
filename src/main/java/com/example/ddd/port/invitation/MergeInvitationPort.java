package com.example.ddd.port.invitation;

import com.example.ddd.model.Invitation;

public interface MergeInvitationPort {
    Invitation merge(Invitation invitation);
}

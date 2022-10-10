package com.example.ddd.port.invitation;

import com.example.ddd.model.Guid;
import com.example.ddd.model.Invitation;

import java.util.Optional;

public interface FindInvitationByIdAndUserIdPort {
    Optional<Invitation> findById(Guid id, Guid userId);
}

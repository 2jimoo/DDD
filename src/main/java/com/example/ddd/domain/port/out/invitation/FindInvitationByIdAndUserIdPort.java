package com.example.ddd.domain.port.out.invitation;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;

import java.util.Optional;

public interface FindInvitationByIdAndUserIdPort {
    Optional<Invitation> findById(Guid id, Guid userId);
}

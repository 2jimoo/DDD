package com.example.ddd.domain.port.out.invitation;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;

import java.util.Optional;

public interface FindInvitationByIdAndReceiverIdPort {
    Optional<Invitation> findByIdAndReceiverId(Guid id, Guid receiverId);
}

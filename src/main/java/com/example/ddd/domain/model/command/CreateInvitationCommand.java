package com.example.ddd.domain.model.command;

import com.example.ddd.domain.model.Guid;

import java.time.Instant;

public record CreateInvitationCommand(Guid senderId, Guid gatheringId, Guid receiverId
        , Instant requestedAt, Guid requestedBy) {
}
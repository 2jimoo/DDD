package com.example.ddd.domain.model.command;

import com.example.ddd.domain.model.Guid;

import java.time.Instant;

public record AcceptInvitationCommand(Guid userId, Guid InvitationId, Instant requestedAt, Guid requestedBy) {
}

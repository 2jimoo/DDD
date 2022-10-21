package com.example.ddd.domain.model.command;

import com.example.ddd.domain.model.Guid;

public record AcceptInvitationCommand(Guid userId, Guid InvitationId) {
}

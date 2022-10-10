package com.example.ddd.model.command;

import com.example.ddd.model.Guid;

public record AcceptInvitationCommand(Guid userId, Guid InvitationId) {
}

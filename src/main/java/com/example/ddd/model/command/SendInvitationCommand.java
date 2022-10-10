package com.example.ddd.model.command;

import com.example.ddd.model.Guid;

import java.time.Instant;
import java.util.Collection;

public record SendInvitationCommand(Guid senderId, Guid gatheringId, Collection<Guid> receiverIds,
                                    Instant requestedAt) {
}

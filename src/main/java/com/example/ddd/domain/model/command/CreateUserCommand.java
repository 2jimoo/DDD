package com.example.ddd.domain.model.command;

import com.example.ddd.domain.model.Guid;

import java.time.Instant;

public record CreateUserCommand(Guid userId, String firstName, String lastName, String email, Instant requestedAt,
                                Guid requestedBy) {
}

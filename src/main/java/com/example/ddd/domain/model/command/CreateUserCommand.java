package com.example.ddd.domain.model.command;

import com.example.ddd.domain.model.Guid;

public record CreateUserCommand(Guid userId, String firstName, String lastName, String email) {
}

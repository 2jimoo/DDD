package com.example.ddd.model.command;

import com.example.ddd.model.Guid;

public record CreateUserCommand(Guid userId, String firstName, String lastName, String email) {
}

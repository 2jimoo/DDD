package com.example.ddd.domain.port.in;

import com.example.ddd.domain.model.User;
import com.example.ddd.domain.model.command.CreateUserCommand;

public interface RegisterUserUseCase {
    User register(CreateUserCommand command);
}

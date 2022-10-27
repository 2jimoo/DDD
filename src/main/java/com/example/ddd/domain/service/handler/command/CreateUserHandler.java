package com.example.ddd.domain.service.handler.command;

import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.User;
import com.example.ddd.domain.model.command.CreateUserCommand;
import com.example.ddd.domain.port.in.RegisterUserUseCase;
import com.example.ddd.domain.port.out.user.FindUserByEmailPort;
import com.example.ddd.domain.port.out.user.PersistUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserHandler implements RegisterUserUseCase {
    private final FindUserByEmailPort findUserByEmailPort;
    private final PersistUserPort persistUserPort;

    @Transactional
    public User register(CreateUserCommand command) {
        User user = User.of(command.userId(), command.firstName(), command.lastName(), Email.of(command.email()),
                () -> findUserByEmailPort.findUserByEmail(Email.of(command.email())).isPresent());
        return persistUserPort.persist(user, command.requestedAt(), command.requestedBy());
    }
}

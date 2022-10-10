package com.example.ddd.handler.command;

import com.example.ddd.model.Email;
import com.example.ddd.model.Guid;
import com.example.ddd.model.User;
import com.example.ddd.model.command.CreateUserCommand;
import com.example.ddd.port.user.FindUserByEmailPort;
import com.example.ddd.port.user.PersistUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserHandler {
    private final FindUserByEmailPort findUserByEmailPort;
    private final PersistUserPort persistUserPort;
    @Transactional
    public Guid handle(CreateUserCommand command) {
        User user = User.of(command.userId(), command.firstName(), command.firstName(), Email.of(command.email()),
                () -> findUserByEmailPort.findUserByEmail(Email.of(command.email())).isPresent());
        User persistedUser = persistUserPort.insert(user);
        return persistedUser.getUserId();
    }
}

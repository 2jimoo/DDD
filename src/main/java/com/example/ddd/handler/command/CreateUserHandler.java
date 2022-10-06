package com.example.ddd.handler.command;

import com.example.ddd.command.CreateUserCommand;
import com.example.ddd.model.Email;
import com.example.ddd.model.Guid;
import com.example.ddd.model.User;
import com.example.ddd.port.user.FindUserByEmailPort;
import com.example.ddd.port.user.PersistUserPort;
import com.example.ddd.specification.UserEmailSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserHandler {
    private final FindUserByEmailPort findUserByEmailPort;
    private final PersistUserPort persistUserPort;

    public Guid handle(CreateUserCommand command) {
        User user = new User(command.userId(), command.firstName(), command.firstName(), Email.of(command.email()));

//        UserEmailSpecification userEmailSpecification = new UserEmailSpecification(findUserByEmailPort);
//        userEmailSpecification.isSatisfiedOrThrow(user);

        User persistedUser = persistUserPort.insert(user);
        return persistedUser.getUserId();
    }
}

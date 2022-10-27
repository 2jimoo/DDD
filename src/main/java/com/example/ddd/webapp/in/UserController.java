package com.example.ddd.webapp.in;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;
import com.example.ddd.domain.model.command.CreateUserCommand;
import com.example.ddd.domain.port.in.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/users")
    User create(@RequestBody CreateUserRequest request) {
        return registerUserUseCase.register(new CreateUserCommand(
                Guid.of(request.userId()),
                request.firstName(),
                request.lastName(),
                request.email(),
                Instant.now(),
                Guid.of(request.requestedBy)
        ));
    }

    record CreateUserRequest(String userId, String firstName, String lastName, String email,
                             String requestedBy) {
    }
}

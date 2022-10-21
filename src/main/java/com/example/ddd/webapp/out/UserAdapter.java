package com.example.ddd.webapp.out;


import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.port.out.user.FindUserByEmailPort;
import com.example.ddd.domain.port.out.user.FindUserByIdPort;
import com.example.ddd.domain.port.out.user.PersistUserPort;
import com.example.ddd.webapp.out.repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements PersistUserPort, FindUserByIdPort, FindUserByEmailPort {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(Email email) {
        return Optional.empty();
    }

    @Override
    public Optional<com.example.ddd.domain.model.User> findUserById(Guid userId) {
        return Optional.empty();
    }

    @Override
    public com.example.ddd.domain.model.User insert(com.example.ddd.domain.model.User user) {
        return null;
    }
}

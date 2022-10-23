package com.example.ddd.webapp.out;


import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;
import com.example.ddd.domain.port.out.user.FindAllUserByIdsPort;
import com.example.ddd.domain.port.out.user.FindUserByEmailPort;
import com.example.ddd.domain.port.out.user.FindUserByIdPort;
import com.example.ddd.domain.port.out.user.PersistUserPort;
import com.example.ddd.webapp.out.repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements FindUserByIdPort, FindAllUserByIdsPort, FindUserByEmailPort, PersistUserPort {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(Email email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Guid userId) {
        return Optional.empty();
    }

    @Override
    public Collection<User> findAllByIds(Collection<Guid> ids) {
        return null;
    }

    @Override
    @Transactional
    public User persist(User user, Instant requestedAt, Guid requestedBy) {
        return null;
    }
}

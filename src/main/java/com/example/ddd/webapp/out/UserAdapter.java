package com.example.ddd.webapp.out;


import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;
import com.example.ddd.domain.port.out.user.FindAllUserByIdsPort;
import com.example.ddd.domain.port.out.user.FindUserByEmailPort;
import com.example.ddd.domain.port.out.user.FindUserByIdPort;
import com.example.ddd.domain.port.out.user.PersistUserPort;
import com.example.ddd.webapp.out.repository.User.UserEntity;
import com.example.ddd.webapp.out.repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements FindUserByIdPort, FindAllUserByIdsPort, FindUserByEmailPort, PersistUserPort {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(Email email) {
        return userRepository.findByEmail(email.email()).map(this::mapToDomainEntity);
    }

    @Override
    public Optional<User> findUserById(Guid userId) {
        return userRepository.findById(userId.guid()).map(this::mapToDomainEntity);
    }

    @Override
    public Collection<User> findAllByIds(Collection<Guid> ids) {
        return userRepository.findAllById(ids.stream().map(Guid::guid).toList()).stream().map(this::mapToDomainEntity).toList();
    }

    @Override
    public User persist(User user, Instant requestedAt, Guid requestedBy) {
        UserEntity savedUserEntity = userRepository.save(mapToDbEntity(user, requestedAt, requestedBy));
        return mapToDomainEntity(savedUserEntity);
    }

    protected UserEntity mapToDbEntity(User user, Instant requestedAt, Guid requestedBy) {
        return new UserEntity(
                user.getUserId().guid(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail().email(),
                requestedAt,
                requestedBy.guid(),
                requestedAt,
                requestedBy.guid()
        );
    }

    protected User mapToDomainEntity(UserEntity userEntity) {
        return new User(
                Guid.of(userEntity.getUserId()),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                Email.of(userEntity.getEmail())
        );
    }
}

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
import com.example.ddd.webapp.out.repository.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements FindUserByIdPort, FindAllUserByIdsPort, FindUserByEmailPort, PersistUserPort {
    private final UserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<User> findUserByEmail(Email email) {
        return userRepository.findByEmail(email.email()).map(userEntityMapper::mapToDomainEntity);
    }

    @Override
    public Optional<User> findUserById(Guid userId) {
        return userRepository.findById(userId.guid()).map(userEntityMapper::mapToDomainEntity);
    }

    @Override
    public Collection<User> findAllByIds(Collection<Guid> ids) {
        return userRepository.findAllById(ids.stream().map(Guid::guid).toList()).stream().map(userEntityMapper::mapToDomainEntity).toList();
    }

    @Override
    public User persist(User user, Instant requestedAt, Guid requestedBy) {
        UserEntity savedUserEntity = userRepository.save(
                userEntityMapper.mapToDbEntity(user, requestedAt, requestedBy));
        return userEntityMapper.mapToDomainEntity(savedUserEntity);
    }

}

package com.example.ddd.webapp.out.repository;

import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;
import com.example.ddd.webapp.out.repository.User.UserEntity;
import com.example.ddd.webapp.out.repository.User.UserRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserEntityMapper {

    public UserEntity mapToDbEntity(User user, Instant requestedAt, Guid requestedBy) {
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

    public User mapToDomainEntity(UserEntity entity) {
        return new UserRepository.UserProxy(
                Guid.of(entity.getUserId()),
                entity.getFirstName(),
                entity.getLastName(),
                Email.of(entity.getEmail())
        );
    }
}

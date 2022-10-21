package com.example.ddd.domain.port.out.user;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;

import java.util.Optional;

public interface FindUserByIdPort {

    Optional<User> findUserById(Guid userId);
}

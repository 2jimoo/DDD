package com.example.ddd.domain.port.out.user;

import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.User;

import java.util.Optional;

public interface FindUserByEmailPort {
    Optional<User> findUserByEmail(Email email);
}

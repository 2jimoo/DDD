package com.example.ddd.domain.port.out.user;

import com.example.ddd.domain.model.Email;
import org.apache.catalina.User;

import java.util.Optional;

public interface FindUserByEmailPort {
    Optional<User> findUserByEmail(Email email);
}

package com.example.ddd.port.user;

import com.example.ddd.model.Email;
import org.apache.catalina.User;

import java.util.Optional;

public interface FindUserByEmailPort {
    Optional<User> findUserByEmail(Email email);
}

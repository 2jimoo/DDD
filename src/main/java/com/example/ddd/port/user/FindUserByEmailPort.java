package com.example.ddd.port.user;

import org.apache.catalina.User;

import java.util.Optional;

public interface FindUserByEmailPort {
    Optional<User> findUserByEmail(String email);
}

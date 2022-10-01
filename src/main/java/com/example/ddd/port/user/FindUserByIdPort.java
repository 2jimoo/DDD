package com.example.ddd.port.user;

import com.example.ddd.model.Guid;
import org.apache.catalina.User;

import java.util.Optional;

public interface FindUserByIdPort {

    Optional<User> findUserById(Guid userId);
}

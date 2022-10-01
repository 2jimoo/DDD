package com.example.ddd.port.user;

import com.example.ddd.model.User;

public interface PersistUserPort {
    User insert(User user);
}

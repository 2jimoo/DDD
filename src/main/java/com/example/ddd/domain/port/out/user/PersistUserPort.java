package com.example.ddd.domain.port.out.user;

import com.example.ddd.domain.model.User;

public interface PersistUserPort {
    User insert(User user);
}

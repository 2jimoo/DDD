

package com.example.ddd.domain.port.out.user;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;

import java.time.Instant;

public interface PersistUserPort {
    User persist(User user, Instant requestedAt, Guid requestedBy);
}
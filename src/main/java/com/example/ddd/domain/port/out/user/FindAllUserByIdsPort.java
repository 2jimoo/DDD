package com.example.ddd.domain.port.out.user;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;

import java.util.Collection;

public interface FindAllUserByIdsPort {
    Collection<User> findAllByIds(Collection<Guid> ids);
}

package com.example.ddd.domain.model.contentprovider;

import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.Guid;

public interface UserContentProvider {
    Guid userId();

    String firstName();

    String lastName();

    Email email();
}

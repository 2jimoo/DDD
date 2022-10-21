package com.example.ddd.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
@AllArgsConstructor
public class User {
    private Guid userId;
    private String firstName;
    private String lastName;
    private Email email;

    public static User of(Guid userId, String firstName, String lastName, Email email, Supplier<Boolean> uniqueEmailValidate) {
        if (uniqueEmailValidate.get()) {
            throw new IllegalArgumentException("Email %s is duplicated.".formatted(email.email()));
        }
        return new User(userId, firstName, lastName, email);
    }
}

package com.example.ddd.domain.model;

import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class User {
    private final Guid userId;
    private final String firstName;
    private final String lastName;
    private final Email email;

    private User(Guid userId, String firstName, String lastName, Email email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static User of(Guid userId, String firstName, String lastName, Email email, Supplier<Boolean> uniqueEmailValidate) {
        if (uniqueEmailValidate.get()) {
            throw new IllegalArgumentException("Email %s is duplicated.".formatted(email.email()));
        }
        return new User(userId, firstName, lastName, email);
    }
}

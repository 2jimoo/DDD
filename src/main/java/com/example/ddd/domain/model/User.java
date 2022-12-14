package com.example.ddd.domain.model;

import com.example.ddd.utils.EntityCommonInfo;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
@EntityCommonInfo(createdAt = "2022-12-13T07:20:31.017493Z", createdBy= "test")
public class User {
    private  Guid userId;
    private  String firstName;
    private  String lastName;
    private  Email email;

    protected User(Guid userId, String firstName, String lastName, Email email) {
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

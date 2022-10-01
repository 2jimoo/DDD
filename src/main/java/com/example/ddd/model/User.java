package com.example.ddd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private Guid userId;
    private String firstName;
    private String lastName;
    private String email;
}

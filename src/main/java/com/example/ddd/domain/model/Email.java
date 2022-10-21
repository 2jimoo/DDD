package com.example.ddd.domain.model;

public record Email(String email) {
    public static Email of(String email) {
        return new Email(email);
    }
}

package com.example.ddd.domain.model;


public record Guid(String guid) {
    public static Guid of(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("id string must be not blank.");
        }
        return new Guid(value);
    }

    @Override
    public String toString() {
        return this.guid;
    }
}

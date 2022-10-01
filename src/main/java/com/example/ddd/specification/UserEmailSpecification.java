package com.example.ddd.specification;

import com.example.ddd.model.User;
import com.example.ddd.port.user.FindUserByEmailPort;

public class UserEmailSpecification {
    private final FindUserByEmailPort findUserByEmailPort;

    public UserEmailSpecification(FindUserByEmailPort findUserByEmailPort) {
        this.findUserByEmailPort = findUserByEmailPort;
    }

    public void isSatisfiedOrThrow(User user) {
        if (!isValidEmailFormat(user.getEmail())) {
            throw new IllegalArgumentException("%s is invalid email address");
        } else if (!isUniqueEmail(user.getEmail())) {
            throw new IllegalArgumentException("%s is duplicated email address");
        }
    }

    private boolean isValidEmailFormat(String email) {
        //email 포맷 검사
        return true;
    }

    private boolean isUniqueEmail(String email) {
        return findUserByEmailPort.findUserByEmail(email).isEmpty();
    }
}

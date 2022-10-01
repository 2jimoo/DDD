package com.example.ddd.specification;

import com.example.ddd.model.Gathering;
import com.example.ddd.port.user.FindUserByIdPort;

public class GatheringCreatorSpecification {
    private final FindUserByIdPort findUserByIdPort;

    public GatheringCreatorSpecification(FindUserByIdPort findUserByIdPort) {
        this.findUserByIdPort = findUserByIdPort;
    }

    public void isSatisfiedOrThrow(Gathering gathering) {
        findUserByIdPort.findUserById(gathering.getCreator()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("User %s cannot be creator.".formatted(gathering.getCreator().guid()));
                }
        );
    }
}

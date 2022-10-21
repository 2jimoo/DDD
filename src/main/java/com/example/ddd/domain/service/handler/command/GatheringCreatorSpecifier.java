package com.example.ddd.domain.service.handler.command;

import com.example.ddd.domain.model.Guid;

public interface GatheringCreatorSpecifier {
    boolean checkUser(Guid userId);
}

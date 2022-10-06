package com.example.ddd.handler.command;

import com.example.ddd.model.Guid;

public interface GatheringCreatorSpecifier {
    boolean checkUser(Guid userId);
}

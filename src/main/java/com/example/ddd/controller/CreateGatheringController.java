package com.example.ddd.controller;

import com.example.ddd.command.CreateGatheringCommand;
import com.example.ddd.handler.command.CreateGatheringHandler;
import com.example.ddd.model.Guid;
import com.example.ddd.port.gathering.PersistGatheringPort;
import com.example.ddd.port.user.FindUserByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CreateGatheringController {

    private final FindUserByIdPort findUserByIdPort;
    private final CreateGatheringHandler createGatheringHandler;

    Guid create(CreateGatheringCommand command){
        findUserByIdPort.findUserById(command.userId()).orElseThrow(
                ()-> {throw new IllegalArgumentException("User %s cannot be creator.".formatted(command.userId()));}
        );
        return createGatheringHandler.handle(command);
    }

}

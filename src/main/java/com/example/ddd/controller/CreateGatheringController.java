package com.example.ddd.controller;

import com.example.ddd.command.CreateGatheringCommand;
import com.example.ddd.handler.command.CreateGatheringHandler;
import com.example.ddd.model.GatheringType;
import com.example.ddd.model.Guid;
import com.example.ddd.port.gathering.PersistGatheringPort;
import com.example.ddd.port.user.FindUserByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CreateGatheringController {

    private final CreateGatheringHandler createGatheringHandler;

    Guid create(CreateGatheringRequest request){
        return createGatheringHandler.handle(
                new CreateGatheringCommand(
                request.name(),request.type(),request.scheduledAt(),request.userId(),request.location(), request.maximumNumberOfAttendees(),
                request.invitationValidBeforeInHours()
        ));
    }

    record CreateGatheringRequest(String name,
                                  GatheringType type,
                                  Instant scheduledAt,
                                  Guid userId,
                                  Optional<String> location,
                                  Optional<Integer> maximumNumberOfAttendees,
                                  Optional<Integer> invitationValidBeforeInHours){}
}

package com.example.ddd.handler.command;

import com.example.ddd.command.CreateGatheringCommand;
import com.example.ddd.model.Gathering;
import com.example.ddd.model.Guid;
import com.example.ddd.port.gathering.PersistGatheringPort;
import com.example.ddd.port.user.FindUserByIdPort;
import com.example.ddd.specification.GatheringCreatorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateGatheringHandler {
    private final FindUserByIdPort findUserByIdPort;
    private final PersistGatheringPort persistGatheringPort;

    public Guid handle(CreateGatheringCommand command) {
        Gathering gathering = Gathering.of(
                new Guid(UUID.randomUUID().toString()), command.type(), command.name(), command.scheduledAt(),
                command.userId(), command.location(), command.maximumNumberOfAttendees(),
                command.invitationValidBeforeInHours(), command.maximumNumberOfAttendees()
        );

        GatheringCreatorSpecification gatheringCreatorSpecification
                = new GatheringCreatorSpecification(findUserByIdPort);
        gatheringCreatorSpecification.isSatisfiedOrThrow(gathering);

        Gathering persistedGathering = persistGatheringPort.insert(gathering);
        return persistedGathering.getId();
    }
}

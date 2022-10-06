package com.example.ddd.handler.command;

import com.example.ddd.command.CreateGatheringCommand;
import com.example.ddd.model.Gathering;
import com.example.ddd.model.Guid;
import com.example.ddd.port.gathering.PersistGatheringPort;
import com.example.ddd.port.user.FindUserByIdPort;
import com.example.ddd.specification.GatheringCreatorSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateGatheringHandler {
    private final FindUserByIdPort findUserByIdPort;
    private final PersistGatheringPort persistGatheringPort;

    public Guid handle(CreateGatheringCommand command) {
        //1. command -> handler 내부에서 검사
        //2. handler 판단값/functional if을 넘기기
        boolean isExistentUser = findUserByIdPort.findUserById(command.userId()).isPresent();

        Gathering gathering = Gathering.of(
                new Guid(UUID.randomUUID().toString()), command.type(), command.name(), command.scheduledAt(),
                command.userId(), command.location(), command.maximumNumberOfAttendees(),
                command.invitationValidBeforeInHours(), command.maximumNumberOfAttendees(),
                () -> {
                    Optional<User> userById = findUserByIdPort.findUserById(command.userId());
                    return userById.isEmpty();
                }

        );

        Gathering persistedGathering = persistGatheringPort.insert(gathering);
        return persistedGathering.getId();
    }

}

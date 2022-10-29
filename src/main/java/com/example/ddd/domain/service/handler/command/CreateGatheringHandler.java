package com.example.ddd.domain.service.handler.command;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;
import com.example.ddd.domain.model.command.CreateGatheringCommand;
import com.example.ddd.domain.port.in.CreateGatheringUseCase;
import com.example.ddd.domain.port.out.gathering.PersistGatheringPort;
import com.example.ddd.domain.port.out.user.FindUserByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateGatheringHandler implements CreateGatheringUseCase {
    private final FindUserByIdPort findUserByIdPort;
    private final PersistGatheringPort persistGatheringPort;

    @Transactional
    public Gathering handle(CreateGatheringCommand command) {
        Gathering gathering = Gathering.of(
                new Guid(UUID.randomUUID().toString()), command.type(), command.name(), command.scheduledAt(),
                command.userId(), command.location(), command.maximumNumberOfAttendees(),
                command.invitationValidBeforeInHours(), command.maximumNumberOfAttendees(),
                () -> {
                    Optional<User> userById = findUserByIdPort.findUserById(command.userId());
                    return userById.isPresent();
                }

        );

        Gathering persistedGathering = persistGatheringPort.persist(gathering, command.requestedAt(), command.requestedBy());
        return persistedGathering;
    }

}

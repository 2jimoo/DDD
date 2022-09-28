package com.example.ddd.handler.commandhandler;

import com.example.ddd.command.Command;
import com.example.ddd.command.CreateGatheringCommand;
import com.example.ddd.handler.CommandHandler;
import com.example.ddd.model.Gathering;
import com.example.ddd.model.GatheringType;
import com.example.ddd.model.Guid;
import com.example.ddd.repository.FindUserByIdPort;
import com.example.ddd.repository.PersistGatheringPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateGatheringCommandHandler implements CommandHandler<CreateGatheringCommand> {
    private final FindUserByIdPort findUserByIdPort;
    private final PersistGatheringPort persistGatheringPort;

    @Override
    public Guid handle(CreateGatheringCommand command) {
        findUserByIdPort.findUserById(command.userId()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("User %s does not exist.".formatted(command.userId().guid()));
                }
        );

        Gathering gathering = Gathering.of(
                new Guid(UUID.randomUUID().toString()),command.type(), command.name(), command.scheduledAt(),
                command.userId(),command.location(), command.maximumNumberOfAttendees(),
                command.invitationValidBeforeInHours(), command.maximumNumberOfAttendees()
        );

    }
}

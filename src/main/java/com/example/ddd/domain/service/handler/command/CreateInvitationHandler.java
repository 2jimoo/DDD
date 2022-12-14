package com.example.ddd.domain.service.handler.command;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.model.User;
import com.example.ddd.domain.model.command.CreateInvitationCommand;
import com.example.ddd.domain.model.event.InvitationCreatedEvent;
import com.example.ddd.domain.port.in.CreateInvitationUseCase;
import com.example.ddd.domain.port.out.gathering.FindGatheringByIdPort;
import com.example.ddd.domain.port.out.gathering.MergeGatheringPort;
import com.example.ddd.domain.port.out.invitation.PersistInvitationPort;
import com.example.ddd.domain.port.out.invitation.SendInvitationPort;
import com.example.ddd.domain.port.out.user.FindUserByIdPort;
import com.example.ddd.domain.service.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateInvitationHandler implements CreateInvitationUseCase {
    private final FindUserByIdPort findUserByIdPort;
    private final FindGatheringByIdPort findGatheringByIdPort;
    private final PersistInvitationPort persistInvitationPort;
    private final EventPublisher<InvitationCreatedEvent> invitationCreatedPublisher;
    private final MergeGatheringPort mergeGatheringPort;

    @Transactional
    public Invitation handle(CreateInvitationCommand command) {
        Optional<Gathering> gathering = findGatheringByIdPort.findById(command.gatheringId());
        Invitation invitation = Invitation.of(
                Guid.of(UUID.randomUUID().toString()),
                command.senderId(),
                command.gatheringId(),
                command.requestedAt(),
                () -> {
                    Optional<User> user = findUserByIdPort.findUserById(command.senderId());
                    if (gathering.isEmpty() || user.isEmpty()) {
                        return false;
                    } else {
                        return Objects.equals(gathering.get().getCreator(), user.get().getUserId());
                    }
                });

        Invitation invitationCreated = persistInvitationPort.persist(invitation, command.requestedAt(), command.requestedBy());
        invitationCreatedPublisher.publish(new InvitationCreatedEvent(invitationCreated));

        Gathering gatheringInvited = gathering.get();
        gatheringInvited.addInvitation(invitationCreated, command.requestedAt());
        mergeGatheringPort.merge(gatheringInvited, command.requestedAt(), command.requestedBy());

        // - invitation persist event ?????? ??????
        //sendInvitationPort.send(invitation);
        return invitationCreated;
    }
}
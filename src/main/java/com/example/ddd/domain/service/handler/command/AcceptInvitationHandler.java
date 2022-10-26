package com.example.ddd.domain.service.handler.command;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.model.command.AcceptInvitationCommand;
import com.example.ddd.domain.model.event.InvitationRespondedEvent;
import com.example.ddd.domain.port.out.gathering.FindGatheringByIdPort;
import com.example.ddd.domain.port.out.invitation.FindInvitationByIdAndReceiverIdPort;
import com.example.ddd.domain.port.out.invitation.MergeInvitationPort;
import com.example.ddd.domain.service.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AcceptInvitationHandler {
    private final FindInvitationByIdAndReceiverIdPort findInvitationByIdAndReceiverIdPort;

    private final FindGatheringByIdPort findGatheringByIdPort;

    private final MergeInvitationPort mergeInvitationPort;

    private final EventPublisher<InvitationRespondedEvent> eventEventPublisher;

    @Transactional
    Guid handle(AcceptInvitationCommand command) {

        //update invitation states
        Invitation invitation = findInvitationByIdAndReceiverIdPort.findByIdAndReceiverId(command.InvitationId(), command.userId()).orElseThrow(() -> {
            throw new IllegalArgumentException("User %s's invitation %s does not exist.".formatted(command.userId(), command.InvitationId()));
        });
        Invitation invitationBeforeRespond = Invitation.copyOf(invitation);
        invitation.accept(
                () -> {
                    Gathering gathering = findGatheringByIdPort.findById(invitation.getGatheringId()).orElseThrow(
                            () -> {
                                throw new IllegalArgumentException("Gathering id %s does not exist.".formatted(invitation.getGatheringId()));
                            }
                    );
                    return invitation.getCreatedAt().isBefore(gathering.getInvitationExpireAt()) && !gathering.isFull();
                }
        );

        //- invitation accepted/expired event 받아 update and merge gathering
        Invitation invitationAfterRespond = mergeInvitationPort.merge(invitation, command.requestedAt(), command.requestedBy());
        eventEventPublisher.publish(new InvitationRespondedEvent(invitationBeforeRespond, invitationAfterRespond));
        return invitationAfterRespond.getId();
    }
}

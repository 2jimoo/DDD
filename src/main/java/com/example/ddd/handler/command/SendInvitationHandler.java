package com.example.ddd.handler.command;

import com.example.ddd.model.Gathering;
import com.example.ddd.model.Guid;
import com.example.ddd.model.Invitation;
import com.example.ddd.model.User;
import com.example.ddd.model.command.SendInvitationCommand;
import com.example.ddd.model.event.InvitationCreatedEvent;
import com.example.ddd.port.gathering.FindGatheringByIdPort;
import com.example.ddd.port.gathering.MergeGatheringPort;
import com.example.ddd.port.invitation.PersistInvitationPort;
import com.example.ddd.port.invitation.SendInvitationPort;
import com.example.ddd.port.user.FindUserByIdPort;
import com.example.ddd.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendInvitationHandler {
    private final FindUserByIdPort findUserByIdPort;
    private final FindGatheringByIdPort findGatheringByIdPort;
    private final PersistInvitationPort persistInvitationPort;

    private final EventPublisher<InvitationCreatedEvent> invitationCreatedPublisher;
    private final MergeGatheringPort mergeGatheringPort;
    private final SendInvitationPort sendInvitationPort;

    @Transactional
    public List<Guid> handle(SendInvitationCommand command) {
        //create invitations.
        List<Invitation> invitations =
                command.receiverIds().stream().map(
                        e -> Invitation.of(
                                Guid.of(UUID.randomUUID().toString()),
                                command.senderId(),
                                command.gatheringId(),
                                command.requestedAt(),
                                () -> {
                                    // functional IF의 인수나 반환값으로 domain model 쓰면 안 되는 이유
                                    // -> 도메인 모델간 침범 문제때문?( 다른 aggregate 변경 등)
                                    // 그럼 VO까지는 supplier로 넘겨도 되나? ex.Supplier<Optional<Guid>> gatheringIdFinder
                                    Optional<User> user = findUserByIdPort.findUserById(command.senderId());
                                    Optional<Gathering> gathering = findGatheringByIdPort.findById(command.gatheringId());
                                    if (gathering.isEmpty() || user.isEmpty()) {
                                        return false;
                                    } else {
                                        return Objects.equals(gathering.get().getCreator(), user.get().getUserId());
                                    }
                                })).filter(Objects::nonNull).toList();

        //persist invitations.
        List<Guid> invitationIds = new ArrayList<>();
        for (Invitation invitation : invitations) {
            Invitation invitationCreated = persistInvitationPort.persist(invitation);
            invitationIds.add(invitationCreated.getId());
            invitationCreatedPublisher.publish(new InvitationCreatedEvent(invitationCreated));
            // - invitation persist event 받아 gathering에 invitaiton 추가
            //gatheringInvited.addInvitation(invitations,command.requestedAt());
            //mergeGatheringPort.merge(gatheringInvited);
            // - invitation persist event 받아 발송
            //sendInvitationPort.send(invitation);
        }
        return invitationIds;
    }
}

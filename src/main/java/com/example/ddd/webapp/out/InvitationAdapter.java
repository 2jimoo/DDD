package com.example.ddd.webapp.out;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.port.out.invitation.FindAllInvitationByIdsPort;
import com.example.ddd.domain.port.out.invitation.FindInvitationByIdAndReceiverIdPort;
import com.example.ddd.domain.port.out.invitation.MergeInvitationPort;
import com.example.ddd.domain.port.out.invitation.PersistInvitationPort;
import com.example.ddd.webapp.out.repository.invitation.InvitationEntity;
import com.example.ddd.webapp.out.repository.invitation.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InvitationAdapter implements PersistInvitationPort, FindInvitationByIdAndReceiverIdPort, FindAllInvitationByIdsPort, MergeInvitationPort {
    private final InvitationRepository invitationRepository;

    @Override
    public Optional<Invitation> findByIdAndReceiverId(Guid id, Guid receiverId) {
        return invitationRepository.findByIdAndReceiverId(id.guid(), receiverId.guid()).map(e -> mapToDomainEntity(e));
    }


    @Override
    public Collection<Invitation> findAllByIds(Collection<Guid> ids) {
        return invitationRepository.findAllById(ids.stream().map(Guid::guid).toList()).stream().map(e -> mapToDomainEntity(e)).toList();
    }

    @Override
    @Transactional
    public Invitation merge(Invitation invitation, Instant requestedAt, Guid requestedBy) {
        InvitationEntity invitationEntity = invitationRepository.findById(invitation.getId().guid()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("Invitation %d is not found.".formatted(invitation.getId().guid()));
                }
        );
        InvitationEntity newInvitationEntity = mapToDbEntity(invitation, requestedAt, requestedBy);

        newInvitationEntity.setInsertedAt(invitationEntity.getInsertedAt());
        newInvitationEntity.setInsertedBy(invitationEntity.getInsertedBy());

        InvitationEntity savedInvitationEntity = invitationRepository.save(newInvitationEntity);
        return mapToDomainEntity(savedInvitationEntity);
    }

    @Override
    public Invitation persist(Invitation invitation, Instant requestedAt, Guid requestedBy) {
        InvitationEntity invitationEntity = invitationRepository.save(mapToDbEntity(invitation, requestedAt, requestedBy));
        return mapToDomainEntity(invitationEntity);
    }

    protected Invitation mapToDomainEntity(InvitationEntity invitationEntity) {
        return new Invitation(
                Guid.of(invitationEntity.getId()),
                Guid.of(invitationEntity.getReceiverId()),
                Guid.of(invitationEntity.getGatheringId()),
                invitationEntity.getStatus(),
                invitationEntity.getCreatedAt()
        );
    }

    protected InvitationEntity mapToDbEntity(Invitation invitation, Instant requestedAt, Guid requestedBy) {
        return new InvitationEntity(
                invitation.getId().guid(),
                invitation.getReceiverId().guid(),
                invitation.getGatheringId().guid(),
                invitation.getStatus(),
                invitation.getCreatedAt(),
                requestedAt,
                requestedBy.guid(),
                requestedAt,
                requestedBy.guid()
        );
    }
}

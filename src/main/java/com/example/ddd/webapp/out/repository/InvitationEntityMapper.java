package com.example.ddd.webapp.out.repository;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.webapp.out.repository.invitation.InvitationEntity;
import com.example.ddd.webapp.out.repository.invitation.InvitationRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class InvitationEntityMapper {

    public InvitationEntity mapToDbEntity(Invitation invitation, Instant requestedAt, Guid requestedBy) {
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

    public Invitation mapToDomainEntity(InvitationEntity entity) {
        return new InvitationRepository.InvitationProxy(
                Guid.of(entity.getId()),
                Guid.of(entity.getReceiverId()),
                Guid.of(entity.getGatheringId()),
                entity.getInsertedAt(),
                entity.getStatus()
        );
    }
}

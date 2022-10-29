package com.example.ddd.webapp.out.repository;

import com.example.ddd.domain.model.Attendee;
import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.webapp.out.repository.Gathering.GatheringEntity;
import com.example.ddd.webapp.out.repository.invitation.InvitationEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class GatheringEntityMapper {
    public GatheringEntity mapToDbEntity(Gathering domainEntity, Instant requestedAt, Guid requestedBy) {
        return new GatheringEntity(
                domainEntity.getId().guid(),
                domainEntity.getType(),
                domainEntity.getName(),
                domainEntity.getScheduledAt(),
                domainEntity.getCreator().guid(),
                domainEntity.getLocation(),
                domainEntity.getMaximumNumberOfAttendees(),
                domainEntity.getInvitationExpireAt(),
                domainEntity.getNumberOfAttendees(),
                domainEntity.getAttendees().stream().map(attendee -> attendee.getUserId().guid()).toList(),
                domainEntity.getInvitations().stream().map(e -> new InvitationEntity(
                        e.getId().guid(),
                        e.getReceiverId().guid(),
                        e.getGatheringId().guid(),
                        e.getStatus(),
                        e.getCreatedAt(),
                        requestedAt,
                        requestedBy.guid(),
                        requestedAt,
                        requestedBy.guid()
                )).toList(),
                requestedAt,
                requestedBy.guid(),
                requestedAt,
                requestedBy.guid()
        );
    }

    public Gathering mapToDomainEntity(GatheringEntity entity) {
        return new Gathering(
                Guid.of(entity.getId()),
                entity.getType(),
                entity.getName(),
                entity.getScheduledAt(),
                Guid.of(entity.getCreator()),
                entity.getLocation(),
                entity.getMaximumNumberOfAttendees(),
                entity.getNumberOfAttendees(),
                new HashSet<>(entity.getInvitations().stream().map(e -> new Attendee(Guid.of(e.getGatheringId()),
                        Guid.of(e.getReceiverId()), e.getCreatedAt())).collect(Collectors.toSet())),
                new HashSet<>(entity.getInvitations().stream().map(e -> new Invitation(
                        Guid.of(e.getId()),
                        Guid.of(e.getReceiverId()),
                        Guid.of(e.getGatheringId()),
                        e.getStatus(),
                        e.getCreatedAt()
                )).collect(Collectors.toSet())),
                entity.getInvitationExpireAt()
        );
    }
}

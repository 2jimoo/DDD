package com.example.ddd.webapp.out;


import com.example.ddd.domain.model.Attendee;
import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.port.out.gathering.FindGatheringByIdPort;
import com.example.ddd.domain.port.out.gathering.MergeGatheringPort;
import com.example.ddd.domain.port.out.gathering.PersistGatheringPort;
import com.example.ddd.domain.port.out.invitation.FindAllInvitationByIdsPort;
import com.example.ddd.webapp.out.repository.Gathering.GatheringEntity;
import com.example.ddd.webapp.out.repository.Gathering.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GatheringAdapter implements FindGatheringByIdPort, MergeGatheringPort, PersistGatheringPort {
    private final GatheringRepository gatheringRepository;
    private final FindAllInvitationByIdsPort findAllInvitationByIdsPort;

    @Override
    public Optional<Gathering> findById(Guid id) {
        return gatheringRepository.findById(id.guid()).map(this::mapToDomainEntity);
    }

    @Override
    @Transactional
    public Optional<Guid> merge(Gathering gathering, Instant requestedAt, Guid requestedBy) {
        Optional<GatheringEntity> gatheringEntity = gatheringRepository.findById(gathering.getId().guid());
        gatheringEntity.ifPresent(
                e -> {
                    //id, creater, createdAt, createdBy 제외한 필드
                    GatheringEntity dbEntity = mapToDbEntity(gathering, requestedAt, requestedBy);
                    dbEntity.setId(e.getId());
                    dbEntity.setCreator(e.getCreator());
                    dbEntity.setInsertedAt(e.getInsertedAt());
                    dbEntity.setInsertedBy(e.getInsertedBy());
                    gatheringRepository.save(e);
                }
        );
        return gatheringRepository.findById(gathering.getId().guid()).map(e -> Guid.of(e.getId()));
    }

    @Override
    @Transactional
    public Gathering persist(Gathering gathering, Instant requestedAt, Guid requestedBy) {
        Optional<GatheringEntity> gatheringEntity = gatheringRepository.findById(gathering.getId().guid());
        if (gatheringEntity.isPresent()) {
            return mapToDomainEntity(gatheringEntity.get());
        } else {
            return mapToDomainEntity(gatheringRepository.save(mapToDbEntity(gathering, requestedAt, requestedBy)));
        }
    }

    protected GatheringEntity mapToDbEntity(Gathering domainEntity, Instant requestedAt, Guid requestedBy) {
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
                domainEntity.getInvitations().stream().map(invitation -> invitation.getId().guid()).toList(),
                requestedAt,
                requestedBy.guid(),
                requestedAt,
                requestedBy.guid()
        );
    }

    protected Gathering mapToDomainEntity(GatheringEntity entity) {
        Collection<Invitation> invitations = findAllInvitationByIdsPort.findAllByIds(
                entity.getInvitationIds().stream().map(Guid::of).toList()
        );
        return new Gathering(
                Guid.of(entity.getId()),
                entity.getType(),
                entity.getName(),
                entity.getScheduledAt(),
                Guid.of(entity.getCreator()),
                entity.getLocation(),
                entity.getMaximumNumberOfAttendees(),
                entity.getInvitationExpireAt(),
                entity.getNumberOfAttendees(),
                invitations.stream().map(e -> new Attendee(e.getGatheringId(), e.getUserId(), e.getCreatedAt())).toList(),
                invitations
        );
    }
}

package com.example.ddd.webapp.out.repository;

import com.example.ddd.domain.model.*;
import com.example.ddd.domain.model.contentprovider.GatheringContentProvider;
import com.example.ddd.domain.model.contentprovider.InvitationContentProvider;
import com.example.ddd.webapp.out.repository.Gathering.GatheringEntity;
import com.example.ddd.webapp.out.repository.invitation.InvitationEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
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
                domainEntity.getAttendees().stream().map(attendee -> attendee.userId().guid()).toList(),
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
        return Gathering.translateOf(
                new GatheringH2dbEntityContentProvider(entity)
        );
    }


    protected class GatheringH2dbEntityContentProvider implements GatheringContentProvider {

        private final GatheringEntity gatheringEntity;

        public GatheringH2dbEntityContentProvider(GatheringEntity gatheringEntity) {
            this.gatheringEntity = gatheringEntity;
        }

        @Override
        public Guid id() {
            return Guid.of(gatheringEntity.getId());
        }

        @Override
        public GatheringType type() {
            return gatheringEntity.getType();
        }

        @Override
        public String name() {
            return gatheringEntity.getName();
        }

        @Override
        public Instant scheduledAt() {
            return gatheringEntity.getScheduledAt();
        }

        @Override
        public Guid creator() {
            return Guid.of(gatheringEntity.getCreator());
        }

        @Override
        public String location() {
            return gatheringEntity.getLocation();
        }

        @Override
        public Integer maximumNumberOfAttendees() {
            return gatheringEntity.getMaximumNumberOfAttendees();
        }

        @Override
        public Integer numberOfAttendees() {
            return gatheringEntity.getMaximumNumberOfAttendees();
        }

        @Override
        public Collection<Attendee> attendees() {
            return gatheringEntity.getInvitations().stream().map(e -> new Attendee(Guid.of(e.getGatheringId()),
                    Guid.of(e.getReceiverId()), e.getCreatedAt())).collect(Collectors.toSet());
        }

        @Override
        public Collection<Invitation> invitations() {
            return gatheringEntity.getInvitations().stream().map(e ->
                    Invitation.translateOf(
                            new InvitationContentProvider() {
                                @Override
                                public Guid id() {
                                    return Guid.of(e.getGatheringId());
                                }

                                @Override
                                public Guid receiverId() {
                                    return Guid.of(e.getReceiverId());
                                }

                                @Override
                                public Guid gatheringId() {
                                    return Guid.of(e.getGatheringId());
                                }

                                @Override
                                public Instant createdAt() {
                                    return e.getCreatedAt();
                                }

                                @Override
                                public InvitationStatus status() {
                                    return e.getStatus();
                                }
                            }
                    )
            ).collect(Collectors.toSet());
        }

        @Override
        public Instant invitationExpireAt() {
            return gatheringEntity.getInvitationExpireAt();
        }
    }
}

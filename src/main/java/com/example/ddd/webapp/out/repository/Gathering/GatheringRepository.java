package com.example.ddd.webapp.out.repository.Gathering;

import com.example.ddd.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collection;

@Repository
public interface GatheringRepository extends JpaRepository<GatheringEntity, String> {
    class GatheringProxy extends Gathering {
        public GatheringProxy(Guid id, GatheringType type, String name, Instant scheduledAt, Guid creator, String location, Integer maximumNumberOfAttendees, Integer numberOfAttendees, Collection<Attendee> attendees, Collection<Invitation> invitations, Instant invitationExpireAt) {
            super(id, type, name, scheduledAt, creator, location, maximumNumberOfAttendees, numberOfAttendees, attendees, invitations, invitationExpireAt);
        }
    }
}

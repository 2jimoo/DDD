package com.example.ddd.domain.model.contentprovider;

import com.example.ddd.domain.model.Attendee;
import com.example.ddd.domain.model.GatheringType;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;

import java.time.Instant;
import java.util.Collection;

public interface GatheringContentProvider {
    Guid id();

    GatheringType type();

    String name();

    Instant scheduledAt();

    Guid creator();

    String location();

    Integer maximumNumberOfAttendees();

    Integer numberOfAttendees();

    Collection<Attendee> attendees();

    Collection<Invitation> invitations();

    Instant invitationExpireAt();
}

package com.example.ddd.model;

import lombok.Getter;

import java.time.Instant;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Optional;

@Getter
public class Gathering {
    private final Guid id;
    private final GatheringType type;
    private final String name;
    private final Instant scheduledAt;
    private final Guid creator;
    private final String location;
    private final Integer maximumNumberOfAttendees;
    private final Instant invitationExpireAt;
    private final Integer numberOfAttendees;
    private final Collection<Attendee> attendees;
    private final Collection<Invitation> invitations;

    public Gathering(Guid id, GatheringType type, String name, Instant scheduledAt, Guid creator, String location,
                     Integer maximumNumberOfAttendees, Instant invitationExpireAt, Integer numberOfAttendees,
                     Collection<Attendee> attendees, Collection<Invitation> invitations) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.scheduledAt = scheduledAt;
        this.creator = creator;
        this.location = location;
        this.maximumNumberOfAttendees = maximumNumberOfAttendees;
        this.invitationExpireAt = invitationExpireAt;
        this.numberOfAttendees = numberOfAttendees;
        this.attendees = attendees;
        this.invitations = invitations;
    }

    public static Gathering of(
            Guid id,
            GatheringType type,
            String name,
            Instant scheduledAt,
            Guid creator,
            Optional<String> location,
            Optional<Integer> maximumNumberOfAttendees,
            Optional<Integer> invitationExpireAt,
            Optional<Integer> numberOfAttendees
    ) {
        if (type == null) {
            throw new IllegalArgumentException("Type is required.");
        } else if (!EnumSet.allOf(GatheringType.class).contains(type)) {
            throw new IllegalArgumentException("%s is Unknown gathering type.".formatted(type.name()));
        }

        if (type.equals(GatheringType.OFFLINE)) {
            maximumNumberOfAttendees.orElseThrow(() ->
                    {
                        throw new IllegalArgumentException("Online gathering needs maximumNumberOfAttendees.");
                    }
            );
        }
        return new Gathering(id, type, name, scheduledAt, creator, location.orElse(null), maximumNumberOfAttendees.orElse(Integer.MAX_VALUE),
                invitationExpireAt.isPresent() ? Instant.ofEpochSecond(invitationExpireAt.get()) : null, numberOfAttendees.isPresent() ? numberOfAttendees.get() : Integer.MAX_VALUE, new HashSet<>(), new HashSet<>());
    }
}

package com.example.ddd.model;

import lombok.Getter;

import java.time.Instant;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Supplier;

@Getter
public class Gathering {
    private final Guid id;
    private final GatheringType type;
    private final String name;
    private final Instant scheduledAt;
    private final Guid creator;
    private final String location;
    private final Integer maximumNumberOfAttendees;
    private Instant invitationExpireAt;
    private Integer numberOfAttendees;
    private Collection<Attendee> attendees;
    private Collection<Invitation> invitations;
    // private Collection<Guid> invitations; Id로만 참조하는게 낫지 않을지. 상태 계속 동기화해야함
    // Invitation이 별도 엔티티는 아닌건가?

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
            Optional<Integer> numberOfAttendees,
            Supplier<Boolean> userValidate
    ) {
        if (!userValidate.get()) {
            throw new IllegalArgumentException("User %s cannot be creator.".formatted(creator.guid()));
        }

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
                invitationExpireAt.isPresent() ? Instant.ofEpochSecond(invitationExpireAt.get()) : null, numberOfAttendees.isPresent() ? numberOfAttendees.get() : 0, new HashSet<>(), new HashSet<>());
    }

    public void addInvitation(Collection<Invitation> invitations, Instant requestedAt) {
        this.invitationExpireAt = Instant.ofEpochSecond(requestedAt.getEpochSecond() + 60 * 60);
        this.invitations.addAll(invitations);
    }

    public boolean isFull() {
        return attendees.size() < maximumNumberOfAttendees;
    }
}

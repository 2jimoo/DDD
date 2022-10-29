package com.example.ddd.domain.model;

import com.example.ddd.exception.Contracts;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Supplier;

@Getter
@AllArgsConstructor
public class Gathering {
    private  Guid id;
    private GatheringType type;
    private String name;
    private Instant scheduledAt;
    private  Guid creator;
    private  String location;
    private  Integer maximumNumberOfAttendees;
    private  Integer numberOfAttendees;
    private Collection<Attendee> attendees;
    private Collection<Invitation> invitations;
    private Instant invitationExpireAt;

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
        Contracts.requires(userValidate.get(), "User %s cannot be creator.".formatted(creator.guid()));

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
        return new Gathering(id, type, name, scheduledAt, creator, location.orElse(null), maximumNumberOfAttendees.orElse(Integer.MAX_VALUE), numberOfAttendees.orElse(0),
                new ArrayList<>(), new ArrayList<>(), invitationExpireAt.map(Instant::ofEpochSecond).orElse(null));
    }

    public void addInvitation(Invitation invitation, Instant requestedAt) {
        this.invitationExpireAt = Instant.ofEpochSecond(requestedAt.getEpochSecond() + 60 * 60);
        this.invitations.add(invitation);
    }

    public boolean isFull() {
        return attendees.size() < maximumNumberOfAttendees;
    }
}

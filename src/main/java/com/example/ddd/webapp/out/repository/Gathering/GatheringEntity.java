package com.example.ddd.webapp.out.repository.Gathering;

import com.example.ddd.domain.model.GatheringType;
import com.example.ddd.webapp.out.Const;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Collection;

@Data
@Entity
@Table(name = Const.GATHERING)
public class GatheringEntity {
    @Id
    private String id;
    private GatheringType type;
    private String name;
    private Instant scheduledAt;
    private String creator;
    private String location;
    private Integer maximumNumberOfAttendees;
    private Instant invitationExpireAt;
    private Integer numberOfAttendees;

    @ElementCollection
    private Collection<String> attendeeIds;

    @ElementCollection
    private Collection<String> invitationIds;
}

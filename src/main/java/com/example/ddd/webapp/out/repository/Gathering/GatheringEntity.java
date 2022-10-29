package com.example.ddd.webapp.out.repository.Gathering;

import com.example.ddd.domain.model.GatheringType;
import com.example.ddd.webapp.out.Const;
import com.example.ddd.webapp.out.repository.invitation.InvitationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Data
@Entity(name = Const.GATHERING)
@Table(name = Const.GATHERINGS)
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(mappedBy = "gatheringId")
    //@JoinColumn(name = "gathering_id")
    private Collection<InvitationEntity> invitations;

    //DB 삽입, 갱신 정보
    private Instant insertedAt;
    private String insertedBy;
    private Instant modifiedAt;
    private String modifiedBy;
}

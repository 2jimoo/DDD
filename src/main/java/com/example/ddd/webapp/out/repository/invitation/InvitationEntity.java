package com.example.ddd.webapp.out.repository.invitation;

import com.example.ddd.domain.model.InvitationStatus;
import com.example.ddd.webapp.out.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity(name = Const.INVITATION)
@Table(name = Const.INVITATIONS)
@NoArgsConstructor
@AllArgsConstructor
public class InvitationEntity {
    @Id
    private String id;
    private String receiverId;

    //@Column(name = "gathering_id")
    private String gatheringId;
    private InvitationStatus status;
    private Instant createdAt;

    //DB 삽입, 갱신 정보
    private Instant insertedAt;
    private String insertedBy;
    private Instant modifiedAt;
    private String modifiedBy;
}

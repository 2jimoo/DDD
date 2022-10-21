package com.example.ddd.webapp.out.repository.invitation;

import com.example.ddd.domain.model.InvitationStatus;
import com.example.ddd.webapp.out.Const;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = Const.INVITATION)
// https://pangtrue.tistory.com/346 Guid를 쓸 수 없나?
// jpa가 no-arg 생성자, non-final 필드, setter 등 record가 제한하는 기능으로 동작하기 때문에
public class InvitationEntity {
    @Id
    private String id;
    private String userId;
    private String gatheringId;
    private InvitationStatus status;
    private Instant createdAt;
}

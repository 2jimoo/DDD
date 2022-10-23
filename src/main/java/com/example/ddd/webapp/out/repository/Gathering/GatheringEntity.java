package com.example.ddd.webapp.out.repository.Gathering;

import com.example.ddd.domain.model.GatheringType;
import com.example.ddd.webapp.out.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Collection;

@Data
@Entity
@Table(name = Const.GATHERING)
@NoArgsConstructor
@AllArgsConstructor
public class GatheringEntity {
    @Id
    private String id;
    // record id 안됨?
    // https://pangtrue.tistory.com/346
    // jpa가 no-arg 생성자, non-final 필드, setter 등 record가 제한하는 기능으로 동작하기 때문에 Guid를 쓸 수 없다?
    private GatheringType type;
    private String name;
    private Instant scheduledAt;
    private String creator;
    private String location;
    private Integer maximumNumberOfAttendees;
    private Instant invitationExpireAt;
    private Integer numberOfAttendees;

    // 객체/콜렉션 필드 안됨?
    // 도메인 엔티티도  Id로만 참조하는게 낫지 않을지? repo에서 기져올 때 매번 조회해와야하는지?
    @ElementCollection
    private Collection<String> attendeeIds;
    @ElementCollection
    private Collection<String> invitationIds;

    //DB 삽입, 갱신 정보
    private Instant insertedAt;
    private String insertedBy;
    private Instant modifiedAt;
    private String modifiedBy;
}

package com.example.ddd.domain.model;

import lombok.Getter;

import java.time.Instant;
import java.util.function.Supplier;

@Getter
public class Invitation {
    private final Guid id;
    private final Guid receiverId;
    private final Guid gatheringId;
    private final Instant createdAt;
    private InvitationStatus status;


    public Invitation(Guid id, Guid receiverId, Guid gatheringId, InvitationStatus status, Instant createdAt) {
        this.id = id;
        this.receiverId = receiverId;
        this.gatheringId = gatheringId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Invitation of(Guid id, Guid receiverId, Guid gatheringId, Instant createdAt, Supplier<Boolean> existentUserIdInExistentGatheringValidator) {

        if (!existentUserIdInExistentGatheringValidator.get()) {
            throw new IllegalArgumentException("receiver %s or gathering %s does not exist.".formatted(receiverId, gatheringId));
        }
        return new Invitation(id, receiverId, gatheringId, InvitationStatus.VALID, createdAt);
    }

    public static Invitation copyOf(Invitation invitation) {
        return new Invitation(invitation.id, invitation.receiverId, invitation.gatheringId, invitation.status, invitation.createdAt);
    }

    // functional IF의 인수나 반환값으로 domain model 쓰면 안 되는 이유
    // -> 도메인 모델간 침범 문제때문?( 다른 aggregate 변경 등)
    // 그럼 VO까지는 supplier로 넘겨도 되나? ex.Supplier<Optional<Guid>> gatheringIdFinder
    public void accept(Supplier<Boolean> notExpiredAndNotFulledGatheringValidator) {
        if (!notExpiredAndNotFulledGatheringValidator.get()) {
            this.status = InvitationStatus.EXPIRED;
        }
    }

}

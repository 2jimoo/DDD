package com.example.ddd.domain.model;

import java.time.Instant;

//유저가 참석자로서 지정된 날 = gatheringId+userId 로 찾아지는 invitation 의 생성일
public record Attendee(Guid gatheringId, Guid userId, Instant registeredAt) {
}

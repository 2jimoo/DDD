package com.example.ddd.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Attendee {
    private Guid gatheringId;
    private Guid userId;
    private Instant registeredAt; //유저가 참석자로서 지정된 날 = gatheringId+userId 로 찾아지는 invitation 의 생성일

}

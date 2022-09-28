package com.example.ddd.model;

import java.time.Instant;

public class Invitation {
    private Guid id;
    private Guid userId;
    private Guid gatheringId;
    private InvitationStatus status;
    private Instant createdAt;
}

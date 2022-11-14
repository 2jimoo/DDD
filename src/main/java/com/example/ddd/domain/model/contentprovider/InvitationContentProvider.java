package com.example.ddd.domain.model.contentprovider;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.InvitationStatus;

import java.time.Instant;

public interface InvitationContentProvider {
    Guid id();

    Guid receiverId();

    Guid gatheringId();

    Instant createdAt();

    InvitationStatus status();
}

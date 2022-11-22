package com.example.ddd.webapp.out.repository.invitation;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.model.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<InvitationEntity, String> {
    Optional<InvitationEntity> findByIdAndReceiverId(String id, String receiverId);

    class InvitationProxy extends Invitation {
        public InvitationProxy(Guid id, Guid receiverId, Guid gatheringId, Instant createdAt, InvitationStatus status) {
            super(id, receiverId, gatheringId, createdAt, status);
        }
    }
}

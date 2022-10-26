package com.example.ddd.webapp.out.repository.invitation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<InvitationEntity, String> {
    Optional<InvitationEntity> findByIdAndReceiverId(String id, String receiverId);
}

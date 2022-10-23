package com.example.ddd.webapp.out;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.port.out.invitation.FindAllInvitationByIdsPort;
import com.example.ddd.domain.port.out.invitation.FindInvitationByIdAndUserIdPort;
import com.example.ddd.domain.port.out.invitation.MergeInvitationPort;
import com.example.ddd.domain.port.out.invitation.PersistInvitationPort;
import com.example.ddd.webapp.out.repository.invitation.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InvitationAdapter implements PersistInvitationPort, FindInvitationByIdAndUserIdPort, FindAllInvitationByIdsPort, MergeInvitationPort {
    private final InvitationRepository invitationRepository;

    @Override
    public Optional<Invitation> findById(Guid id, Guid userId) {
        return Optional.empty();
    }


    @Override
    public Collection<Invitation> findAllByIds(Collection<Guid> ids) {
        return null;
    }

    @Override
    @Transactional
    public Invitation merge(Invitation invitation, Instant requestedAt, Guid requestedBy) {
        return null;
    }

    @Override
    @Transactional
    public Invitation persist(Invitation invitation, Instant requestedAt, Guid requestedBy) {
        return null;
    }
}

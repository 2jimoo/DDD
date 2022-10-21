package com.example.ddd.webapp.out;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.port.out.invitation.FindInvitationByIdAndUserIdPort;
import com.example.ddd.domain.port.out.invitation.MergeInvitationPort;
import com.example.ddd.domain.port.out.invitation.PersistInvitationPort;
import com.example.ddd.webapp.out.repository.invitation.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InvitationAdapter implements PersistInvitationPort, FindInvitationByIdAndUserIdPort, MergeInvitationPort {
    private final InvitationRepository invitationRepository;

    @Override
    public Optional<Invitation> findById(Guid id, Guid userId) {
        return Optional.empty();
    }

    @Override
    public Invitation merge(Invitation invitation) {
        return null;
    }

    @Override
    public Invitation persist(Invitation invitation) {
        return null;
    }
}

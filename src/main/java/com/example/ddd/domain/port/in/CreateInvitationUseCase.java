package com.example.ddd.domain.port.in;

import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.model.command.CreateInvitationCommand;

public interface CreateInvitationUseCase {
    Invitation handle(CreateInvitationCommand command);
}

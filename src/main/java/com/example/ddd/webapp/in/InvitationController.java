package com.example.ddd.webapp.in;

import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.Invitation;
import com.example.ddd.domain.model.command.CreateInvitationCommand;
import com.example.ddd.domain.port.in.CreateInvitationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class InvitationController {
    private final CreateInvitationUseCase createInvitationUseCase;

    @PostMapping("/invitations")
    Invitation create(@RequestBody CreateInvitationRequest request) {
        return createInvitationUseCase.handle(new CreateInvitationCommand(
                Guid.of(request.senderId()),
                Guid.of(request.gatheringId()),
                Guid.of(request.receiverId()),
                Instant.now(),
                Guid.of(request.senderId())
        ));
    }

    record CreateInvitationRequest(
            String senderId, String gatheringId, String receiverId) {
    }
}

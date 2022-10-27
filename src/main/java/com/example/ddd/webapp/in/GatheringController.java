package com.example.ddd.webapp.in;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.GatheringType;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.command.CreateGatheringCommand;
import com.example.ddd.domain.port.in.CreateGatheringUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GatheringController {
    private final CreateGatheringUseCase createGatheringUseCase;

    @PostMapping("/gatherings")
    Gathering create(@RequestBody CreateGatheringRequest request) {
        return createGatheringUseCase.handle(
                new CreateGatheringCommand(
                        request.name(),
                        GatheringType.valueOf(request.type()),
                        request.scheduledAt(),
                        Guid.of(request.userId()),
                        Optional.ofNullable(ObjectUtils.isEmpty(request.location()) ? null : request.location()),
                        Optional.ofNullable(ObjectUtils.isEmpty(request.maximumNumberOfAttendees()) ? null : request.maximumNumberOfAttendees()),
                        Optional.ofNullable(ObjectUtils.isEmpty(request.invitationValidBeforeInHours()) ? null : request.invitationValidBeforeInHours()),
                        Instant.now(),
                        Guid.of(request.userId())
                )
        );
    }

    record CreateGatheringRequest(@NonNull String name,
                                  @NonNull String type,
                                  @NonNull Instant scheduledAt,
                                  @NonNull String userId,
                                  @Nullable String location,
                                  @Nullable Integer maximumNumberOfAttendees,
                                  @Nullable Integer invitationValidBeforeInHours) {
    }
}

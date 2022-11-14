package com.example.ddd.webapp.in;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.GatheringType;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.command.CreateGatheringCommand;
import com.example.ddd.domain.port.in.CreateGatheringUseCase;
import com.example.ddd.domain.port.in.GetGatheringByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/gatherings")
@RequiredArgsConstructor
public class GatheringController {
    private final CreateGatheringUseCase createGatheringUseCase;
    private final GetGatheringByIdUseCase getGatheringByIdUseCase;

    @GetMapping("/{id}")
    Gathering get(@PathVariable String id) {
        return getGatheringByIdUseCase.getById(Guid.of(id)).orElseThrow(
                () -> {
                    throw new RuntimeException("Gathering %s is not found.".formatted(id));
                }
        );
    }


    @PostMapping
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
                                  @NonNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant scheduledAt,
                                  //2007-02-08
                                  @NonNull String userId,
                                  @Nullable String location,
                                  @Nullable Integer maximumNumberOfAttendees,
                                  @Nullable Integer invitationValidBeforeInHours) {
    }
}

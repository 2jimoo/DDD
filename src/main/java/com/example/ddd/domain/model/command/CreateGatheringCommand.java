package com.example.ddd.domain.model.command;

import com.example.ddd.domain.model.GatheringType;
import com.example.ddd.domain.model.Guid;

import java.time.Instant;
import java.util.Optional;

public record CreateGatheringCommand(String name,
                                     GatheringType type,
                                     Instant scheduledAt,
                                     Guid userId,
                                     Optional<String> location,
                                     Optional<Integer> maximumNumberOfAttendees,
                                     Optional<Integer> invitationValidBeforeInHours) {
}
package com.example.ddd.model.command;

import com.example.ddd.model.GatheringType;
import com.example.ddd.model.Guid;

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
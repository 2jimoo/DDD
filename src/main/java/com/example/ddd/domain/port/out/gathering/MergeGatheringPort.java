package com.example.ddd.domain.port.out.gathering;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;

import java.time.Instant;
import java.util.Optional;

public interface MergeGatheringPort {

    Optional<Guid> merge(Gathering gathering, Instant requestedAt, Guid requestedBy);
}

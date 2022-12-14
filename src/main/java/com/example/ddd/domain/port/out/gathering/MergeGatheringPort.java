package com.example.ddd.domain.port.out.gathering;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;

import java.time.Instant;

public interface MergeGatheringPort {

    Gathering merge(Gathering gathering, Instant requestedAt, Guid requestedBy);
}
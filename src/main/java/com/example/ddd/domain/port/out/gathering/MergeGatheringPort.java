package com.example.ddd.domain.port.out.gathering;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;

public interface MergeGatheringPort {
    Guid merge(Gathering gathering);
}

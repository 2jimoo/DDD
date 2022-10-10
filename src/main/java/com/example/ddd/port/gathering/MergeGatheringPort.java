package com.example.ddd.port.gathering;

import com.example.ddd.model.Gathering;
import com.example.ddd.model.Guid;

public interface MergeGatheringPort {
    Guid merge(Gathering gathering);
}

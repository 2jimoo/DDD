package com.example.ddd.port.gathering;

import com.example.ddd.model.Gathering;
import com.example.ddd.model.Guid;

import java.util.Optional;

public interface FindGatheringByIdPort {
    Optional<Gathering> findById(Guid id);
}

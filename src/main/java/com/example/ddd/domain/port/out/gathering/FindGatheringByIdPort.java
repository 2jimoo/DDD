package com.example.ddd.domain.port.out.gathering;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;

import java.util.Optional;

public interface FindGatheringByIdPort {
    Optional<Gathering> findById(Guid id);
}

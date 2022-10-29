package com.example.ddd.domain.port.in;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;

import java.util.Optional;

public interface GetGatheringByIdUseCase {
    Optional<Gathering> getById(Guid id);
}

package com.example.ddd.domain.service;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.port.in.GetGatheringByIdUseCase;
import com.example.ddd.domain.port.out.gathering.FindGatheringByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GatheringService implements GetGatheringByIdUseCase {

    private final FindGatheringByIdPort findGatheringByIdPort;

    @Override
    public Optional<Gathering> getById(Guid id) {
        return findGatheringByIdPort.findById(id);
    }
}

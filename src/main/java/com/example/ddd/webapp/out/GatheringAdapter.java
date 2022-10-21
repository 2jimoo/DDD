package com.example.ddd.webapp.out;


import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.port.out.gathering.FindGatheringByIdPort;
import com.example.ddd.domain.port.out.gathering.MergeGatheringPort;
import com.example.ddd.domain.port.out.gathering.PersistGatheringPort;
import com.example.ddd.webapp.out.repository.Gathering.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GatheringAdapter implements PersistGatheringPort, FindGatheringByIdPort, MergeGatheringPort {
    private final GatheringRepository gatheringRepository;

    @Override
    public Optional<Gathering> findById(Guid id) {
        return Optional.empty();
    }

    @Override
    public Guid merge(Gathering gathering) {
        return null;
    }

    @Override
    public Gathering insert(Gathering gathering) {
        return null;
    }
}

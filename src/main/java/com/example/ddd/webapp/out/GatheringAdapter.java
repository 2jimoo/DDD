package com.example.ddd.webapp.out;


import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.port.out.gathering.FindGatheringByIdPort;
import com.example.ddd.domain.port.out.gathering.MergeGatheringPort;
import com.example.ddd.domain.port.out.gathering.PersistGatheringPort;
import com.example.ddd.webapp.out.repository.Gathering.GatheringEntity;
import com.example.ddd.webapp.out.repository.Gathering.GatheringRepository;
import com.example.ddd.webapp.out.repository.GatheringEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GatheringAdapter implements FindGatheringByIdPort, MergeGatheringPort, PersistGatheringPort {
    private final GatheringRepository gatheringRepository;

    private final GatheringEntityMapper gatheringEntityMapper;

    @Override
    public Optional<Gathering> findById(Guid id) {
        return gatheringRepository.findById(id.guid()).map(gatheringEntityMapper::mapToDomainEntity);
    }

    @Override
    @Transactional
    public Gathering merge(Gathering gathering, Instant requestedAt, Guid requestedBy) {
        GatheringEntity gatheringEntity = gatheringRepository.findById(gathering.getId().guid()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("Gathering %d is not found.".formatted(gathering.getId().guid()));
                }
        );
        GatheringEntity newGatheringEntity = gatheringEntityMapper.mapToDbEntity(gathering, requestedAt, requestedBy);

        newGatheringEntity.setInsertedAt(gatheringEntity.getInsertedAt());
        newGatheringEntity.setInsertedBy(gatheringEntity.getInsertedBy());

        GatheringEntity savedGatheringEntity = gatheringRepository.save(newGatheringEntity);
        return gatheringEntityMapper.mapToDomainEntity(savedGatheringEntity);
    }

    @Override
    public Gathering persist(Gathering gathering, Instant requestedAt, Guid requestedBy) {
        //insert도 update로 한다는데...??
        GatheringEntity savedGatheringEntity = gatheringRepository.save(gatheringEntityMapper.mapToDbEntity(gathering, requestedAt, requestedBy));
        return gatheringEntityMapper.mapToDomainEntity(savedGatheringEntity);
    }
}

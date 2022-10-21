package com.example.ddd.domain.port.out.gathering;

import com.example.ddd.domain.model.Gathering;

public interface PersistGatheringPort {

    Gathering insert(Gathering gathering);
}

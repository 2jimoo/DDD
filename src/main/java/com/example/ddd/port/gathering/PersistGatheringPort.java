package com.example.ddd.port.gathering;

import com.example.ddd.model.Gathering;

public interface PersistGatheringPort {

    Gathering insert(Gathering gathering);
}

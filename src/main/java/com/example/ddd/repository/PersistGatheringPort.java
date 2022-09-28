package com.example.ddd.repository;

import com.example.ddd.model.Gathering;
import com.example.ddd.model.Guid;

public interface PersistGatheringPort {

    Gathering insert(Gathering gathering);
}

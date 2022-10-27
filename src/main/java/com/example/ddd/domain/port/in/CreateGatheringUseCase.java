package com.example.ddd.domain.port.in;

import com.example.ddd.domain.model.Gathering;
import com.example.ddd.domain.model.command.CreateGatheringCommand;

public interface CreateGatheringUseCase {
    Gathering handle(CreateGatheringCommand command);
}

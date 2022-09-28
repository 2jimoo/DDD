package com.example.ddd.handler;

import com.example.ddd.command.Command;
import com.example.ddd.model.Guid;

public interface CommandHandler<C extends Command> {
    Guid handle(C command);
}

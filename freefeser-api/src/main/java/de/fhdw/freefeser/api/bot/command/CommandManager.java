package de.fhdw.freefeser.api.bot.command;

import java.util.Collection;

public interface CommandManager {

    Collection<Command> getCommands();

    void registerCommand(Command command);

    void unregisterCommand(Command command);
}

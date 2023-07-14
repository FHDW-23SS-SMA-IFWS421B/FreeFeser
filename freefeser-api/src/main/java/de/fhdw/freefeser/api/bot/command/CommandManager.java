package de.fhdw.freefeser.api.bot.command;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface CommandManager {

    Collection<Command> getCommands();

    boolean registerCommand(Command command);

    boolean unregisterCommand(Command command);

    /**
     * This method executes a command by raw input console text, if present.
     *
     * @param rawText input text from console
     * @return command if executed
     */
    CompletableFuture<Command> executeCommand(String rawText);
}

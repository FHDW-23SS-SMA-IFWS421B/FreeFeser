package de.fhdw.freefeser.app.bot.command;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.command.Command;
import de.fhdw.freefeser.api.bot.command.CommandManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AppCommandManager implements CommandManager {

    private final Collection<Command> commands;

    public AppCommandManager() {
        this.commands = new ArrayList<>();
    }

    @Override
    public Collection<Command> getCommands() {
        return this.commands;
    }

    @Override
    public boolean registerCommand(Command command) {
        Optional<Command> existingCommand = this.commands.stream().filter(c -> c.getName().equalsIgnoreCase(command.getName())).findFirst();
        if(existingCommand.isPresent()) {
            //@Todo throw exception
            return false;
        }
        this.commands.add(command);
        return true;
    }

    @Override
    public boolean unregisterCommand(Command command) {
        return this.commands.remove(command);
    }

    @Override
    public CompletableFuture<Command> executeCommand(String rawText) {
        Chatbot extractedBot = null;//@Todo extract bot
        if(extractedBot == null) {
            return null;
        }

        CompletableFuture<Command> future = new CompletableFuture<>();
        //@Todo wait on all futures
        for(Command command : this.commands) {
            if(!command.getOwner().equals(extractedBot)) {
                continue;
            }
            command.check(rawText).thenAccept(result -> {
                if(result) {
                    future.complete(command);
                }
            });
        }

        return future;
    }
}

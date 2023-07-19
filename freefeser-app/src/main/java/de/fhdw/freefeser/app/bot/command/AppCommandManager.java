package de.fhdw.freefeser.app.bot.command;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.command.Command;
import de.fhdw.freefeser.api.bot.command.CommandManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AppCommandManager implements CommandManager {

    private final List<Command> commands;

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
            //return null;
        }

        List<Command> botCommands = this.commands;//@Todo extract only bot commands

        CompletableFuture<Command> future = new CompletableFuture<>();

        CompletableFuture<Boolean>[] commandChecks = new CompletableFuture[botCommands.size()];



        int index = 0;
        String[] commandCheckTextRaw = rawText.split(" ", 2);
        if(commandCheckTextRaw.length != 2) {
            return CompletableFuture.completedFuture(null);
        }

        String commandCheckText = commandCheckTextRaw[1];
        for(Command command : botCommands) {
            commandChecks[index] = command.check(commandCheckText);
            index++;
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(commandChecks);

        allFutures.thenRun(() -> {
            int checkIndex = 0;
            for (CompletableFuture<Boolean> check : commandChecks) {
                Boolean result = null; // Das Ergebnis des CompletableFuture abrufen
                try {
                    result = check.get();
                    if(result) {
                        Command executedCommand = botCommands.get(checkIndex);
                        executedCommand.onExecute(null, commandCheckText);//@Todo add translation and sender
                        future.complete(executedCommand);
                        break;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                checkIndex++;
            }
            future.complete(null);
        });

        return future;
    }
}

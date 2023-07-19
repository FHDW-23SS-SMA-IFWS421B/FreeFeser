package de.fhdw.freefeser.app.console.callbacks;

import de.fhdw.freefeser.api.bot.command.CommandManager;
import de.fhdw.freefeser.api.console.ConsoleReader;

public class CommandManagerConsoleReaderCallback extends AppConsoleReaderCallback {

    private final CommandManager commandManager;

    public CommandManagerConsoleReaderCallback(ConsoleReader reader, CommandManager commandManager) {
        super(reader);
        this.commandManager = commandManager;
    }

    @Override
    public void onInputReceived(String input) {
        this.commandManager.executeCommand(input);
    }
}

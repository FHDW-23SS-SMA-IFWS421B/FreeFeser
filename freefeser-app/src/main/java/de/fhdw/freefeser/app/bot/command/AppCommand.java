package de.fhdw.freefeser.app.bot.command;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.command.Command;

public abstract class AppCommand implements Command {

    private final Chatbot owner;
    private final String name;
    private final String description;

    public AppCommand(Chatbot owner, String name, String description) {
        this.owner = owner;
        this.name = name;
        this.description = description;
    }

    @Override
    public Chatbot getOwner() {
        return this.owner;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}

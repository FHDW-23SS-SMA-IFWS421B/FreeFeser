package de.fhdw.freefeser.api.bot;


import de.fhdw.freefeser.api.bot.command.CommandManager;

public interface Chatbot {

    String getName();

    CommandManager getCommandManager();
}

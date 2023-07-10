package de.fhdw.freefeser.api.bot;


import de.fhdw.freefeser.api.bot.command.CommandManager;

import java.util.concurrent.CompletableFuture;

public interface Chatbot {

    String getName();

    CompletableFuture<Boolean> isEnabled();

    CommandManager getCommandManager();
}

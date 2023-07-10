package de.fhdw.freefeser.api.bot.command;

import de.fhdw.freefeser.api.bot.Chatbot;

import java.util.concurrent.CompletableFuture;

public interface Command {

    Chatbot getOwner();

    String getName();

    String getDescription();

    CompletableFuture<Boolean> check(String rawText);

    void onExecute(CommandSender sender, String rawText, Object... args);
}

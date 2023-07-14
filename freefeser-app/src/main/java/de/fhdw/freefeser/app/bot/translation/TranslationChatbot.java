package de.fhdw.freefeser.app.bot.translation;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.command.CommandManager;
import de.fhdw.freefeser.app.bot.command.AppCommandManager;

import java.util.concurrent.CompletableFuture;

public class TranslationChatbot implements Chatbot {

    private final CommandManager commandManager;

    public TranslationChatbot() {
        this.commandManager = new AppCommandManager();
    }

    @Override
    public String getName() {
        return "TranslationChatbot";
    }

    @Override
    public CompletableFuture<Boolean> isEnabled() {
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }
}

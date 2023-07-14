package de.fhdw.freefeser.app.bot.translation;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.command.CommandSender;
import de.fhdw.freefeser.app.bot.command.AppCommand;

import java.util.concurrent.CompletableFuture;

public class TranslationCommand extends AppCommand {
    public TranslationCommand(Chatbot owner, String name, String description) {
        super(owner, name, description);
    }

    @Override
    public CompletableFuture<Boolean> check(String rawText) {
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public void onExecute(CommandSender sender, String rawText, Object... args) {

    }
}

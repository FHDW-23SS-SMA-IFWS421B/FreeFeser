package de.fhdw.freefeser.app.chatbot.translation.commands;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.command.CommandSender;
import de.fhdw.freefeser.app.bot.command.AppCommand;
import de.fhdw.freefeser.app.chatbot.translation.DeepLTranslationApi;
import de.fhdw.freefeser.app.chatbot.translation.TranslationApi;

import java.util.concurrent.CompletableFuture;

public class TranslationCommand extends AppCommand {

    public TranslationCommand(Chatbot owner, String name, String description) {
        super(owner, name, description);
    }

    @Override
    public CompletableFuture<Boolean> check(String rawText) {
        return CompletableFuture.completedFuture(true);//The translation bot only has one command, the translation command
    }

    @Override
    public void onExecute(CommandSender sender, String rawText, Object... args) {
        TranslationApi translationApi = new DeepLTranslationApi();//@Todo optimize
        //translationApi.translate(rawText)
        System.out.println(("The translation for " + rawText + " is Reckow"));
    }
}

package de.fhdw.freefeser.app.chatbot.translation;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.chatbot.AppChatbot;

public class TranslationAppChatbot extends AppChatbot {

    private final TranslationApi translationApi;
    public TranslationAppChatbot(ConsolePrinter printer) {
        super(printer, "translationbot");
        this.translationApi = new DeepLTranslationApi();
    }

    @Override
    public void onExecute(User sender, String rawText) {
        translationApi.translate("en", rawText).thenAccept(result -> {
           sendMessageOnBehalf("Die Übersetzung ist: " + result.getTranslation());
        });
    }
}

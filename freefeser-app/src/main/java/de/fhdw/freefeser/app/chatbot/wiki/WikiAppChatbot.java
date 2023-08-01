package de.fhdw.freefeser.app.chatbot.wiki;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.chatbot.AppChatbot;

public class WikiAppChatbot extends AppChatbot {

    public WikiAppChatbot(ConsolePrinter printer) {
        super(printer, "wikibot");
    }

    @Override
    public void onExecute(User sender, String rawText) {

    }
}

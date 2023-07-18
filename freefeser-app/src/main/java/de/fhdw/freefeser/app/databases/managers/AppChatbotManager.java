package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;

public class AppChatbotManager implements ChatbotManager {

    @Override
    public boolean registerBot(Chatbot chatbot) {
        // make isEnabled true?
        return false;
    }

    @Override
    public boolean unregisterBot(Chatbot chatbot) {
        // make isEnabled false?
        return false;
    }
}

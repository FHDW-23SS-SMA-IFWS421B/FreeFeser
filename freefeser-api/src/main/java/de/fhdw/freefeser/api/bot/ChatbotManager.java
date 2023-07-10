package de.fhdw.freefeser.api.bot;

public interface ChatbotManager {

    boolean registerBot(Chatbot chatbot);

    boolean unregisterBot(Chatbot chatbot);
}

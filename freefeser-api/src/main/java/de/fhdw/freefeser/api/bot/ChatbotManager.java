package de.fhdw.freefeser.api.bot;

import de.fhdw.freefeser.api.user.User;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface ChatbotManager {

    Collection<Chatbot> getBots();

    void registerBot(Chatbot chatbot);

    void unregisterBot(Chatbot chatbot);

    Chatbot executeCommand(User sender, String text);
}

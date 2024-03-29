package de.fhdw.freefeser.api.bot;

import de.fhdw.freefeser.api.user.User;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface ChatbotManager {

    Collection<Chatbot> getBots();

    CompletableFuture<Void> registerBot(Chatbot chatbot);

    void unregisterBot(Chatbot chatbot);

    void executeCommand(User sender, String text);
}

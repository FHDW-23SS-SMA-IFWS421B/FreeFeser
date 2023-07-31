package de.fhdw.freefeser.api.bot;

import de.fhdw.freefeser.api.user.User;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface ChatbotManager {

    Collection<Chatbot> getBots();

    boolean registerBot(Chatbot chatbot);

    boolean unregisterBot(Chatbot chatbot);

    Chatbot executeCommand(User sender, String text);
}

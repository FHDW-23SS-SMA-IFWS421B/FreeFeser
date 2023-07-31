package de.fhdw.freefeser.api.bot;

import de.fhdw.freefeser.api.user.User;

import java.util.concurrent.CompletableFuture;

public interface Chatbot {

    String getName();

    CompletableFuture<Boolean> isEnabled();

    void setEnabled(boolean enabled);

    void onExecute(User sender, String rawText);

    void sendMessageOnBehalf(String message);
}

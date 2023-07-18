package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.database.ChatMessage;
import de.fhdw.freefeser.api.database.ChatMessageManager;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AppChatMessageManager implements ChatMessageManager {

    @Override
    public CompletableFuture<ChatMessage> load(UUID id) {
        // SELECT Statement?
        return null;
    }

    @Override
    public CompletableFuture<Void> save(ChatMessage message) {
        // UPDATE Statement?
        return null;
    }

    @Override
    public CompletableFuture<ChatMessage> create(ChatMessage entityWithoutId) {
        // INSERT Statement?
        return null;
    }

    @Override
    public CompletableFuture<Void> delete(UUID id) {
        // DELETE Statement?
        return null;
    }
}

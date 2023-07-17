package de.fhdw.freefeser.api.database;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ChatMessageManager extends DatabaseManager<ChatMessage> {

    CompletableFuture<ChatMessage> load(UUID id);

    CompletableFuture<Void> save(ChatMessage message);

    CompletableFuture<ChatMessage> create(ChatMessage entityWithoutId);

    CompletableFuture<Void> delete(UUID id);
}

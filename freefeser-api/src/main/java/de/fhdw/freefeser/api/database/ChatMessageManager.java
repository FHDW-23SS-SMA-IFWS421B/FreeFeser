package de.fhdw.freefeser.api.database;

import java.util.concurrent.CompletableFuture;

public interface ChatMessageManager extends DatabaseManager<ChatMessage> {

    CompletableFuture<ChatMessage> load(long id);

    CompletableFuture<Void> save(ChatMessage message);

    CompletableFuture<ChatMessage> create(ChatMessage entityWithoutId);

    CompletableFuture<Void> delete(long id);
}

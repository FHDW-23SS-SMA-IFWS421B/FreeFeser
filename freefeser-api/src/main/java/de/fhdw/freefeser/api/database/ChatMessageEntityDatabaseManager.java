package de.fhdw.freefeser.api.database;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ChatMessageEntityDatabaseManager<U extends UserEntity, C extends ChatbotEntity> extends DatabaseManager<ChatMessageEntity<U, C>> {

    CompletableFuture<List<ChatMessageEntity<U, C>>> getAll(String username);
}

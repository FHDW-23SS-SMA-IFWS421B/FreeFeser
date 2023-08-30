package de.fhdw.freefeser.api.database;

import java.util.concurrent.CompletableFuture;

public interface ChatbotEntityDatabaseManager extends DatabaseManager<ChatbotEntity> {

    CompletableFuture<ChatbotEntity> getByName(String name);
}

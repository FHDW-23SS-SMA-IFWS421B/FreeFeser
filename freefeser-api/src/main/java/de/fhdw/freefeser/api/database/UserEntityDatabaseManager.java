package de.fhdw.freefeser.api.database;

import java.util.concurrent.CompletableFuture;

public interface UserEntityDatabaseManager extends DatabaseManager<UserEntity> {

    CompletableFuture<UserEntity> getByUsername(String username);

}

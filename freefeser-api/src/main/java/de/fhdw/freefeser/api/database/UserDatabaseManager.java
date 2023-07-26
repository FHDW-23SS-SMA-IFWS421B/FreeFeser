package de.fhdw.freefeser.api.database;

import java.util.concurrent.CompletableFuture;

public interface UserDatabaseManager extends DatabaseManager<User> {

    CompletableFuture<User> getByUsername(String username);

}

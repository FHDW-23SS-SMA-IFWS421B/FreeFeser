package de.fhdw.freefeser.api.database;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserManager extends DatabaseManager<User> {

    CompletableFuture<User> load(UUID id);

    CompletableFuture<Void> save(User user);

    CompletableFuture<User> create(User entityWithoutId);

    CompletableFuture<Void> delete(UUID id);
}

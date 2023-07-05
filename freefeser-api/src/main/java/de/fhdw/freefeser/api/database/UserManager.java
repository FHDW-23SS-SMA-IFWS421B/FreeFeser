package de.fhdw.freefeser.api.database;

import java.util.concurrent.CompletableFuture;

public interface UserManager extends DatabaseManager<User> {

    CompletableFuture<User> load(long id);

    CompletableFuture<Void> save(User user);

    CompletableFuture<User> create(User entityWithoutId);

    CompletableFuture<Void> delete(long id);
}

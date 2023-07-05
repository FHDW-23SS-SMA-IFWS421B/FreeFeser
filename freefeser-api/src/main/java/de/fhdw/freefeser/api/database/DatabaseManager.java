package de.fhdw.freefeser.api.database;

import java.util.concurrent.CompletableFuture;

public interface DatabaseManager<T> {

    CompletableFuture<T> load(long id);

    CompletableFuture<Void> save(T entity);

    CompletableFuture<T> create(T entityWithoutId);

    CompletableFuture<Void> delete(long id);
}

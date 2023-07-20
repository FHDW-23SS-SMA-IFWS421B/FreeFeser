package de.fhdw.freefeser.api.database;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface DatabaseManager<T> {

    CompletableFuture<List<T>> getAll();

    CompletableFuture<T> get(UUID id);

    CompletableFuture<Void> update(T entity);

    CompletableFuture<T> create(T entityWithoutId);

    CompletableFuture<Void> delete(UUID id);
}

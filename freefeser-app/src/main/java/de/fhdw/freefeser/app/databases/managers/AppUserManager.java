package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.database.User;
import de.fhdw.freefeser.api.database.UserDatabaseManager;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AppUserManager implements UserDatabaseManager {

    @Override
    public CompletableFuture<User> getAll() {
        return null;
    }

    @Override
    public CompletableFuture<User> get(UUID id) {
        // SELECT Statement?
        return null;
    }

    @Override
    public CompletableFuture<Void> update(User user) {
        // UPDATE Statement?
        return null;
    }

    @Override
    public CompletableFuture<User> create(User entityWithoutId) {
        // INSERT VALUE Statement?
        return null;
    }

    @Override
    public CompletableFuture<Void> delete(UUID id) {
        // DELETE Statement?
        return null;
    }
}

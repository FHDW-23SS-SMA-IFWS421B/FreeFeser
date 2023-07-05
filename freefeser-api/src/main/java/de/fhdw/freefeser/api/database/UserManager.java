package de.fhdw.freefeser.api.database;

import java.util.concurrent.CompletableFuture;

public interface UserManager {

    CompletableFuture<User> loadUser(long id);

    CompletableFuture<Void> saveUser(User user);

    CompletableFuture<User> createUser(String username, String password);

    CompletableFuture<Void> deleteUser(long id);
}

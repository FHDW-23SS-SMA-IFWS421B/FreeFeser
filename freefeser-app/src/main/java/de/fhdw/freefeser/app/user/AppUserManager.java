package de.fhdw.freefeser.app.user;

import de.fhdw.freefeser.api.database.UserDatabaseManager;
import de.fhdw.freefeser.api.user.UserManager;

import java.util.concurrent.CompletableFuture;

public class AppUserManager implements UserManager {

    private final UserDatabaseManager userDatabaseManager;

    public AppUserManager(UserDatabaseManager userDatabaseManager) {
        this.userDatabaseManager = userDatabaseManager;
    }

    @Override
    public CompletableFuture<Boolean> login(String username, String password) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        userDatabaseManager.getByUsername(username).thenAcceptAsync(user -> {
            if(user == null || !user.getPassword().equals(password)) {
                future.complete(false);
            } else {
                future.complete(true);
            }
        });

        return future;
    }
}

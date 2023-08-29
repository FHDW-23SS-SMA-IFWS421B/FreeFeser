package de.fhdw.freefeser.api.user;

import java.util.concurrent.CompletableFuture;

public interface UserManager {

    /**
     * If no user logged in, null will be returned
     * @return logged in user or null
     */
    User getLoggedInUser();

    CompletableFuture<User> login(String username, String password);

    /**
     *
     * @param username
     * @param password
     * @return future with null, if a user with username already exists
     */
    CompletableFuture<User> register(String username, String password);
}

package de.fhdw.freefeser.api.user;

import java.util.concurrent.CompletableFuture;

public interface UserManager {

    /**
     * If no user logged in, null will be returned
     * @return logged in user or null
     */
    User getLoggedInUser();

    CompletableFuture<User> login(String username, String password);

}

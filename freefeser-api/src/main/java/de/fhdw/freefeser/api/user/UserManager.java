package de.fhdw.freefeser.api.user;

import java.util.concurrent.CompletableFuture;

public interface UserManager {

    CompletableFuture<Boolean> login(String username, String password);
}

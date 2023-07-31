package de.fhdw.freefeser.app.user;

import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.database.UserEntityDatabaseManager;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.console.reader.callbacks.ChatbotManagerConsoleReaderCallback;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AppUserManager implements UserManager {

    private final UserEntityDatabaseManager userDatabaseManager;
    private final ConsolePrinter printer;
    private final ConsoleReader reader;
    private final Consumer<UserManager> onLogin;

    private User loggedInUser;

    public AppUserManager(UserEntityDatabaseManager userDatabaseManager, ConsolePrinter printer, ConsoleReader reader, Consumer<UserManager> onLogin) {
        this.userDatabaseManager = userDatabaseManager;
        this.onLogin = onLogin;
        this.printer = printer;
        this.reader = reader;
    }

    @Override
    public User getLoggedInUser() {
        return null;
    }

    @Override
    public CompletableFuture<User> login(String username, String password) {
        CompletableFuture<User> future = new CompletableFuture<>();

        userDatabaseManager.getByUsername(username).thenAcceptAsync(user -> {
            if(user == null || !user.getPassword().equals(password)) {
                future.complete(null);
            } else {
                User newUser = new AppUser(user, this.printer);
                this.loggedInUser = newUser;
                this.onLogin.accept(this);
                future.complete(newUser);
            }
        });

        return future;
    }
}

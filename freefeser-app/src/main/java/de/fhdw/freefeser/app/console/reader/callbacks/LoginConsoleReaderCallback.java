package de.fhdw.freefeser.app.console.reader.callbacks;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.database.UserEntity;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.databases.entities.AppUser;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class LoginConsoleReaderCallback extends AppConsoleReaderCallback {

    private String user;

    private final UserManager userManager;
    private final ConsolePrinter printer;


    //@Todo add user manager and remove login consumer
    public LoginConsoleReaderCallback(ConsoleReader reader, ConsolePrinter printer, UserManager userManager) {
        super(reader);
        this.userManager = userManager;
        this.printer = printer;
    }

    @Override
    public void onInputReceived(String input) {
        if(user == null) {
            user = input;
        } else {
            isCorrectPassword(this.user, input).thenAcceptAsync(user -> {
                if(user != null) {
                    this.unregister();
                } else {
                    this.printer.println("Wrong password");
                }
            });

        }
    }

    private CompletableFuture<User> isCorrectPassword(String username, String password) {
        return this.userManager.login(username, password);
    }
}

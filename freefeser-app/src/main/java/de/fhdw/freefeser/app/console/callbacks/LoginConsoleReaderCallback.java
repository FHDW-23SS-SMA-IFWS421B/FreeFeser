package de.fhdw.freefeser.app.console.callbacks;

import de.fhdw.freefeser.api.console.ConsoleReader;
import de.fhdw.freefeser.api.database.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.databases.entities.AppUser;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class LoginConsoleReaderCallback extends AppConsoleReaderCallback {

    private String user;

    private final UserManager userManager;
    private final Consumer<User> onLoginSuccess;


    //@Todo add user manager and remove login consumer
    public LoginConsoleReaderCallback(ConsoleReader reader, UserManager userManager, Consumer<User> onLoginSuccess) {
        super(reader);
        this.userManager = userManager;
        this.onLoginSuccess = onLoginSuccess;
    }

    @Override
    public void onInputReceived(String input) {
        System.out.println("LOGIN "+ input);
        if(user == null) {
            user = input;
        } else {
            isCorrectPassword(this.user, input).thenAcceptAsync(correct -> {
                if(correct) {
                    System.out.println("Correct password");
                    onLoginSuccess.accept(new AppUser());//@Todo callback in user manager
                    this.unregister();
                } else {
                    System.out.println("Wrong password");
                }
            });

        }
    }

    private CompletableFuture<Boolean> isCorrectPassword(String username, String password) {
        return this.userManager.login(username, password);
    }
}

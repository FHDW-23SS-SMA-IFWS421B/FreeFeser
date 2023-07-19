package de.fhdw.freefeser.app.console.callbacks;

import de.fhdw.freefeser.api.console.ConsoleReader;
import de.fhdw.freefeser.api.console.ConsoleReaderCallback;
import de.fhdw.freefeser.api.database.User;
import de.fhdw.freefeser.app.console.AppConsoleReader;
import de.fhdw.freefeser.app.databases.entities.AppUser;

import java.util.function.Consumer;

public class LoginConsoleReaderCallback extends AppConsoleReaderCallback {

    private String user;
    private String password;

    private final Consumer<User> onLoginSuccess;


    //@Todo add user manager and remove login consumer
    public LoginConsoleReaderCallback(ConsoleReader reader, Consumer<User> onLoginSuccess) {
        super(reader);
        this.onLoginSuccess = onLoginSuccess;
    }

    @Override
    public void onInputReceived(String input) {
        System.out.println("LOGIN "+ input);
        if(user == null && doesUserExist(input)) {
            user = input;
        } else if(user != null && isCorrectPassword(input)) {
            password = input;
            onLoginSuccess.accept(new AppUser());//@Todo callback in user manager
            this.unregister();
        }
    }

    private boolean doesUserExist(String user) {
        return true;
    }

    private boolean isCorrectPassword(String password) {
        return true;
    }
}

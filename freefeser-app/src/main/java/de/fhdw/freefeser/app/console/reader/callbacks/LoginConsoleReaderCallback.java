package de.fhdw.freefeser.app.console.reader.callbacks;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;

import java.util.concurrent.CompletableFuture;

public class LoginConsoleReaderCallback extends AppConsoleReaderCallback {

    private Boolean register;
    private String user;

    private final UserManager userManager;
    private final ConsolePrinter printer;

    public LoginConsoleReaderCallback(ConsoleReader reader, ConsolePrinter printer, UserManager userManager) {
        super(reader);
        this.userManager = userManager;
        this.printer = printer;

        this.printer.println("Möchten sie einen neuen Benutzer erstellen? (Ja/Nein)");
    }

    @Override
    public void onInputReceived(String input) {
        if(register == null) {
            if(input.equalsIgnoreCase("ja")) {
                this.register = true;
                this.printer.println("Bitte geben sie den Benutzernamen für den neuen Benutzer ein:");
            } else if(input.equalsIgnoreCase("nein")) {
                this.register = false;
                this.printer.println("Bitte geben sie den Benutzernamen ein:");
            } else {
                this.printer.println("Falsche Eingabe. Verwende Ja/Nein.");
            }
        } else if(user == null) {
            user = input;
            if(register) {
                this.printer.println("Bitte geben sie das gewünschte Passwort für ihren neuen Benutzer ein:");
            } else {
                this.printer.println("Bitte geben sie das korrekte Passwort für ihren Benutzer ein:");
            }
        } else {
            if(this.register) {
                this.userManager.register(this.user, input).thenAcceptAsync(user -> {
                   if(user == null) {
                       this.printer.println("A user with the username" + this.user + " already exist.");
                       this.user = null;
                   } else {
                       this.printer.println("Der Benutzer wurde erfolgreich erstellt.");
                       this.unregister();
                   }
                });
            } else {
                this.userManager.login(this.user, input).thenAcceptAsync(user -> {
                    if(user != null) {
                        this.printer.println("Login erfolgreich.");
                        this.unregister();
                    } else {
                        this.printer.println("Falsches Passwort.");
                    }
                });
            }
        }
    }


}

package de.fhdw.freefeser.app.console.reader.callbacks;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.user.UserManager;

public class LoginConsoleReaderCallback extends AppConsoleReaderCallback {

    private Boolean register;
    private String user;

    private final UserManager userManager;
    private final ConsolePrinter printer;

    public LoginConsoleReaderCallback(ConsoleReader reader, ConsolePrinter printer, UserManager userManager) {
        super(reader);
        this.userManager = userManager;
        this.printer = printer;

        this.printer.println("[system] Möchten Sie einen neuen Benutzer erstellen? (Ja/Nein)");
    }

    @Override
    public void onInputReceived(String input) {
        if(register == null) {
            if(input.equalsIgnoreCase("ja")) {
                this.register = true;
                this.printer.println("[system] Bitte geben Sie den Benutzernamen für den neuen Benutzer ein:");
            } else if(input.equalsIgnoreCase("nein")) {
                this.register = false;
                this.printer.println("[system] Bitte geben Sie den Benutzernamen ein:");
            } else {
                this.printer.println("[system] Falsche Eingabe. Verwenden Sie Ja/Nein.");
            }
        } else if(user == null) {
            user = input;
            if(register) {
                this.printer.println("[system] Bitte geben Sie das gewünschte Passwort für ihren neuen Benutzer ein:");
            } else {
                this.printer.println("[system] Bitte geben Sie das korrekte Passwort für ihren Benutzer ein:");
            }
        } else {
            if(this.register) {
                this.userManager.register(this.user, input).thenAcceptAsync(user -> {
                   if(user == null) {
                       this.printer.println("[system] A user with the username " + this.user + " already exist.");
                       this.user = null;
                   } else {

                       this.unregister();
                   }
                });
            } else {
                this.userManager.login(this.user, input).thenAcceptAsync(user -> {
                    if(user != null) {
                        this.unregister();
                    } else {
                        this.printer.println("[system] Falsches Passwort.", false);
                        this.printer.println("[system] Bitte geben Sie das korrekte Passwort für ihren Benutzer ein:");
                    }
                });
            }
        }
    }


}

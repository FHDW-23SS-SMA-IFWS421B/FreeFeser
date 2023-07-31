package de.fhdw.freefeser.app.user;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.database.UserEntity;
import de.fhdw.freefeser.api.user.User;

public class AppUser implements User {

    private final UserEntity entity;
    private final ConsolePrinter printer;

    public AppUser(UserEntity entity, ConsolePrinter printer) {
        this.entity = entity;
        this.printer = printer;
    }


    @Override
    public void sendMessage(String message) {
        this.printer.println("["+this.entity.getUsername()+"] " + message);
    }

    @Override
    public UserEntity getEntity() {
        return this.entity;
    }
}

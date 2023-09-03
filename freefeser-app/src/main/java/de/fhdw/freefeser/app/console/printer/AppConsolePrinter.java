package de.fhdw.freefeser.app.console.printer;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.databases.entities.AppChatMessageEntity;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;

import java.time.LocalDateTime;

public class AppConsolePrinter implements ConsolePrinter {

    private AppChatMessageDatabaseManager chatMessageDatabaseManager;
    private UserManager userManager;

    public AppConsolePrinter() {

    }

    @Override
    public void println(String value) {
        //saveMessage(value);
        println(value, true);
    }

    @Override
    public void println(String value, boolean askForInput) {
        System.out.println(value);
        if(askForInput) {
            User loggedInUser = this.userManager.getLoggedInUser();
            String userName = loggedInUser == null ? "user" : loggedInUser.getEntity().getUsername();
            print("["+userName+"] ");
        }

    }

    @Override
    public void print(String value) {
        //saveMessage(value);
        System.out.print(value);
    }

    private void saveMessage(String value) {
        if(!value.startsWith("[system]") && this.userManager != null && this.chatMessageDatabaseManager != null && this.userManager.getLoggedInUser() != null) {
            User user = this.userManager.getLoggedInUser();
            AppChatMessageEntity messageEntity = new AppChatMessageEntity(value, LocalDateTime.now(), user.getEntity());
            //this.chatMessageDatabaseManager.create(messageEntity);
        }
    }

    public void setChatMessageDatabaseManager(AppChatMessageDatabaseManager chatMessageDatabaseManager) {
        this.chatMessageDatabaseManager = chatMessageDatabaseManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}

package de.fhdw.freefeser.app.chatbot;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.database.ChatbotEntity;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.databases.entities.AppChatMessageEntity;
import de.fhdw.freefeser.app.databases.entities.AppChatbotEntity;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public abstract class AppChatbot implements Chatbot {

    private final ConsolePrinter printer;
    private final String name;
    private final UserManager userManager;
    private final AppChatMessageDatabaseManager chatMessageDatabaseManager;
    private final ChatbotEntityDatabaseManager databaseManager;

    public AppChatbot(ConsolePrinter printer, String name, UserManager userManager, AppChatMessageDatabaseManager chatMessageDatabaseManager, ChatbotEntityDatabaseManager databaseManager) {
        this.printer = printer;
        this.name = name;
        this.userManager = userManager;
        this.chatMessageDatabaseManager = chatMessageDatabaseManager;
        this.databaseManager = databaseManager;
    }

    protected void sendErrorMessage() {
        this.sendMessageOnBehalf("Ein unerwarteter Fehler ist aufgetreten. Versuchen Sie es später erneut! ;-)");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CompletableFuture<Boolean> isEnabled() {
        return load().thenApply(ChatbotEntity::getStatus);
    }

    @Override
    public void setEnabled(boolean enabled) {
        load().thenAccept(entity -> {
           entity.setStatus(enabled);
            this.databaseManager.update(entity);
        });
    }

    @Override
    public void sendMessageOnBehalf(String message) {
        sendMessageOnBehalf(message, true);
    }

    @Override
    public void sendMessageOnBehalf(String message, boolean askForInput) {
        String formattedMessage = "["+getName()+"] "+ message;
        printer.println(formattedMessage);
    }

    private CompletableFuture<ChatbotEntity> load() {
        return this.databaseManager.getByName(this.name);
    }
}

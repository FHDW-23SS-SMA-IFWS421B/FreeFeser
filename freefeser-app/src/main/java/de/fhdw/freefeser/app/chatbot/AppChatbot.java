package de.fhdw.freefeser.app.chatbot;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.databases.entities.AppChatMessageEntity;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageManager;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public abstract class AppChatbot implements Chatbot {

    private final ConsolePrinter printer;
    private final String name;
    private final UserManager userManager;
    private final AppChatMessageManager chatMessageManager;
    private final ChatbotEntityDatabaseManager databaseManager;

    public AppChatbot(ConsolePrinter printer, String name, UserManager userManager, AppChatMessageManager chatMessageManager, ChatbotEntityDatabaseManager databaseManager) {
        this.printer = printer;
        this.name = name;
        this.userManager = userManager;
        this.chatMessageManager = chatMessageManager;
        this.databaseManager = databaseManager;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CompletableFuture<Boolean> isEnabled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEnabled(boolean enabled) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessageOnBehalf(String message) {
        String formattedMessage = "["+getName()+"] "+ message;

        this.databaseManager.getByName(this.name).thenAccept(bot -> {
            AppChatMessageEntity chatMessageEntity = new AppChatMessageEntity(formattedMessage, LocalDateTime.now(), this.userManager.getLoggedInUser().getEntity(), bot);
            this.chatMessageManager.create(chatMessageEntity).thenAccept(createdMessage -> {
                printer.println(formattedMessage);
            });
        });
    }
}

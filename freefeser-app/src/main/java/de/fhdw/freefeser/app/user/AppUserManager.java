package de.fhdw.freefeser.app.user;

import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.database.ChatMessageEntity;
import de.fhdw.freefeser.api.database.UserEntity;
import de.fhdw.freefeser.api.database.UserEntityDatabaseManager;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.console.reader.callbacks.ChatbotManagerConsoleReaderCallback;
import de.fhdw.freefeser.app.databases.entities.AppChatbotEntity;
import de.fhdw.freefeser.app.databases.entities.AppUserEntity;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageManager;

import java.util.concurrent.CompletableFuture;

public class AppUserManager implements UserManager {

    private final UserEntityDatabaseManager userDatabaseManager;
    private final ChatbotManager chatbotManager;
    private final AppChatMessageManager chatMessageManager;
    private final ConsolePrinter printer;
    private final ConsoleReader reader;
    private User loggedInUser;

    public AppUserManager(UserEntityDatabaseManager userDatabaseManager, ChatbotManager chatbotManager, AppChatMessageManager chatMessageManager, ConsolePrinter printer, ConsoleReader reader) {
        this.userDatabaseManager = userDatabaseManager;
        this.chatbotManager = chatbotManager;
        this.chatMessageManager = chatMessageManager;
        this.printer = printer;
        this.reader = reader;
    }

    @Override
    public User getLoggedInUser() {
        return this.loggedInUser;
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
                onLogin(newUser);
                future.complete(newUser);
            }
        });

        return future;
    }

    @Override
    public CompletableFuture<User> register(String username, String password) {
        CompletableFuture<User> future = new CompletableFuture<>();

        userDatabaseManager.getByUsername(username).thenAcceptAsync(user -> {
            if(user == null) {
                UserEntity entity = new AppUserEntity(username, password);
                this.userDatabaseManager.create(entity).thenAcceptAsync(createdEntity -> {
                    User newUser = new AppUser(createdEntity, printer);
                    this.loggedInUser = newUser;
                    onLogin(newUser);
                    future.complete(newUser);
                });
            } else {
                future.complete(null);
            }
        });

        return future;
    }

    private void onLogin(User user) {
        this.reader.addCallback(new ChatbotManagerConsoleReaderCallback(reader, this.chatbotManager, this, chatMessageManager));
        this.chatMessageManager.getAll().thenAccept(messages -> {
            for (ChatMessageEntity<AppUserEntity, AppChatbotEntity> message : messages) {
                if(message.getChatbot() == null) {
                    printer.println("["+user.getEntity().getUsername()+"] " + message.getText());
                } else {
                    System.out.println(message.getChatbot());
                    printer.println("["+message.getChatbot().getBotname()+"] " + message.getText());
                }
            }
        });
    }
}

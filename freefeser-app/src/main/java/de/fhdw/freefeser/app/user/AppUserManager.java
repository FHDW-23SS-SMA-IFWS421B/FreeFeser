package de.fhdw.freefeser.app.user;

import de.fhdw.freefeser.api.bot.Chatbot;
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
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;

import java.util.concurrent.CompletableFuture;

public class AppUserManager implements UserManager {

    private final UserEntityDatabaseManager userDatabaseManager;
    private final ChatbotManager chatbotManager;
    private final AppChatMessageDatabaseManager chatMessageDatabaseManager;
    private final ConsolePrinter printer;
    private final ConsoleReader reader;
    private User loggedInUser;

    public AppUserManager(UserEntityDatabaseManager userDatabaseManager, ChatbotManager chatbotManager, AppChatMessageDatabaseManager chatMessageDatabaseManager, ConsolePrinter printer, ConsoleReader reader) {
        this.userDatabaseManager = userDatabaseManager;
        this.chatbotManager = chatbotManager;
        this.chatMessageDatabaseManager = chatMessageDatabaseManager;
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
                onLogin(newUser, false).thenAccept(success -> {
                    this.loggedInUser = newUser;
                    future.complete(newUser);
                });
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
                    onLogin(newUser, true).thenAccept(success -> {
                        this.loggedInUser = newUser;
                        future.complete(newUser);
                    });
                });
            } else {
                future.complete(null);
            }
        });

        return future;
    }

    private CompletableFuture<Void> onLogin(User user, boolean register) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        if(register) {
            this.printer.println("[system] Der Benutzer wurde erfolgreich erstellt.", false);
        } else {
            this.printer.println("[system] Login erfolgreich.", false);
        }
        this.reader.addCallback(new ChatbotManagerConsoleReaderCallback(reader, this.chatbotManager, this, chatMessageDatabaseManager));
        this.chatMessageDatabaseManager.getAll(user.getEntity().getUsername()).thenAccept(messages -> {
            for (ChatMessageEntity<AppUserEntity, AppChatbotEntity> messageHistory : messages) {
                String message = messageHistory.getText();
                String prefix = isBotMessage(message) ? "" : "[" + user.getEntity().getUsername() + "] ";

                this.printer.println(prefix + message, false);
            }
            this.printer.println("====================", false);
            this.printer.println("[system] Herzlich Willkommen im Chatbot-System! Nutzen Sie den !help-Befehl oder geben Sie einen anderen Befehl/Frage ein:", false);
            this.printer.print("["+user.getEntity().getUsername()+"] ");
            future.complete(null);
        });
        return future;
    }

    private boolean isBotMessage(String message) {
        if(message.startsWith("[system]")) {
            return true;
        }

        for (Chatbot bot : this.chatbotManager.getBots()) {
            if(message.startsWith("["+bot.getName()+"]")) {
                return true;
            }
        }
        return false;
    }
}

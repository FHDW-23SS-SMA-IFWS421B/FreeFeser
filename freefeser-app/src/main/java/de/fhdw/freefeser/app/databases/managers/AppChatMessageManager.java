package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.database.ChatMessage;
import de.fhdw.freefeser.api.database.ChatMessageDatabaseManager;
import de.fhdw.freefeser.app.databases.entities.AppChatbot;
import de.fhdw.freefeser.app.databases.entities.AppUser;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AppChatMessageManager implements ChatMessageDatabaseManager<AppUser, AppChatbot> {


    @Override
    public CompletableFuture<ChatMessage<AppUser, AppChatbot>> getAll() {
        return null;
    }

    @Override
    public CompletableFuture<ChatMessage<AppUser, AppChatbot>> get(UUID id) {
        return null;
    }

    @Override
    public CompletableFuture<Void> update(ChatMessage<AppUser, AppChatbot> entity) {
        return null;
    }

    @Override
    public CompletableFuture<ChatMessage<AppUser, AppChatbot>> create(ChatMessage<AppUser, AppChatbot> entityWithoutId) {
        return null;
    }

    @Override
    public CompletableFuture<Void> delete(UUID id) {
        return null;
    }
}

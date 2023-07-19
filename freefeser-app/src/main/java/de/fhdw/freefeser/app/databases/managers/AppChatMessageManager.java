package de.fhdw.freefeser.app.databases.managers;

import de.fhdw.freefeser.api.database.ChatMessage;
import de.fhdw.freefeser.api.database.ChatMessageManager;
import de.fhdw.freefeser.app.databases.entities.AppChatbot;
import de.fhdw.freefeser.app.databases.entities.AppUser;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AppChatMessageManager implements ChatMessageManager<AppUser, AppChatbot> {


    @Override
    public CompletableFuture<ChatMessage<AppUser, AppChatbot>> load(UUID id) {
        return null;
    }

    @Override
    public CompletableFuture<Void> save(ChatMessage<AppUser, AppChatbot> entity) {
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

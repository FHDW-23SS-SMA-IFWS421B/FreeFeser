package de.fhdw.freefeser.api.database;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ChatMessageManager<U extends User, C extends Chatbot> extends DatabaseManager<ChatMessage<U, C>> {


}

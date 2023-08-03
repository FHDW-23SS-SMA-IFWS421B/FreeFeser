package de.fhdw.freefeser.app.chatbot.wiki;

import java.util.concurrent.CompletableFuture;

public interface WikiApi {

    CompletableFuture<WikiResult[]> search(String query);
}

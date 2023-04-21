package de.fhdw.freefeser.app.chatbot.wiki;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface WikiApi {

    CompletableFuture<WikiResult[]> search(String query) throws InterruptedException, ExecutionException;
}

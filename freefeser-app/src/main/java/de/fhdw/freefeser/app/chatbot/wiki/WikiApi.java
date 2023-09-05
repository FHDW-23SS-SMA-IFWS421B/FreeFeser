package de.fhdw.freefeser.app.chatbot.wiki;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface WikiApi {

    CompletableFuture<AppWikiResult[]> search(String query);
}

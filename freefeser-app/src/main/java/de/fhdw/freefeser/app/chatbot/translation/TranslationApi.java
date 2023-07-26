package de.fhdw.freefeser.app.chatbot.translation;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface TranslationApi {

    CompletableFuture<TranslationResult> translate(String destinationLanguage, String value);
}

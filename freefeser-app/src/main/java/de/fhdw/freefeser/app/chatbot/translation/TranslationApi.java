package de.fhdw.freefeser.app.chatbot.translation;

import java.util.concurrent.CompletableFuture;

public interface TranslationApi {

    CompletableFuture<String> translate(String sourceLanguage, String destinationLanguage, String value);

    CompletableFuture<String> translate(String destinationLanguage, String value);
}

package de.fhdw.freefeser.app.chatbot.translation;

import java.util.concurrent.CompletableFuture;

public interface TranslationApi {

    CompletableFuture<String> translate(String destinationLanguage, String value);
}

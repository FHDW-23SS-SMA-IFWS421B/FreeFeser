package de.fhdw.freefeser.app.chatbot.translation;

import java.util.concurrent.CompletableFuture;

public interface TranslationApi {

    CompletableFuture<TranslationResult> translate(String destinationLanguage, String value);
}

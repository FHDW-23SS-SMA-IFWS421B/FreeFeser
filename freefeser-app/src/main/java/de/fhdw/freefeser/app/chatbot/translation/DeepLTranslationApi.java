package de.fhdw.freefeser.app.chatbot.translation;

import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;

public class DeepLTranslationApi implements TranslationApi {

    @Override
    public CompletableFuture<String> translate(String destinationLanguage, String value) {
        HttpClient client = null;

        return client.sendAsync(null, null).thenApply((result) -> {

            String resultS = "";
            return resultS;
        });
    }
}

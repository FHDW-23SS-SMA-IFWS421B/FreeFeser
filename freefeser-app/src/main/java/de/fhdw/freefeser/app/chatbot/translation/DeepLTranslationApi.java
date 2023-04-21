package de.fhdw.freefeser.app.chatbot.translation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DeepLTranslationApi implements TranslationApi {

    @Override
    public CompletableFuture<HttpResponse<String>> translate(String targetLanguage, String value) throws InterruptedException, ExecutionException {
        String endpoint = "https://api.deepl.com/v2/translate";
        String apiKey = "3f881fa8-df8f-647c-9658-5cdf3e8f85ac:fx";
        String params = "auth_key=" + apiKey + "&text=" + value + "&target_lang=" + targetLanguage;
        String url = endpoint + "?" + params;

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> {
            System.out.println(response.body());
            return response;
        });
    }
}

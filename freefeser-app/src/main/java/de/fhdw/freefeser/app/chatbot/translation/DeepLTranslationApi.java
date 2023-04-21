package de.fhdw.freefeser.app.chatbot.translation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DeepLTranslationApi implements TranslationApi {

    @Override
    public CompletableFuture<HttpResponse<String>> translate(String destinationLanguage, String value) throws InterruptedException, ExecutionException {
        String apiUrl = "";

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> {
            System.out.println(response.body());
            return response;
        });
    }
}

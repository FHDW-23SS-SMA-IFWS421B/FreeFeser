package de.fhdw.freefeser.app.chatbot.wiki;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class MediaWikiApi implements WikiApi {
    private static final Gson gson = new Gson();

    @Override
    public CompletableFuture<WikiResult[]> search(String keyword) {
        String endpoint = "https://de.wikipedia.org/w/rest.php/v1/search/";
        String params =  "page?q=" + keyword + "&limit=5";
        String url = endpoint + params;

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply((HttpResponse<String> response) -> {
            System.out.println(response.body());
            return gson.fromJson(response.body(), WikiResult[].class);
        }).exceptionally((Throwable throwable) -> {
            // Handle any exceptions that occurred during the API call
            throw new CompletionException(throwable);
        });
    }
}

package de.fhdw.freefeser.app.chatbot.wiki;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class MediaWikiApi implements WikiApi {
    @Override
    public CompletableFuture<WikiResult[]> search(String keyword) {
        String endpoint = "https://de.wikipedia.org/w/rest.php/v1/search/";
        String params = "page?q=" + keyword + "&limit=5";
        String url = endpoint + params;

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> {
            System.out.println(response.body());
            return (WikiResult[]) response;
        });
    }
}

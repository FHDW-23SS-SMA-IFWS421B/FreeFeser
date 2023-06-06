package de.fhdw.freefeser.app.chatbot.wiki;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MediaWikiApi implements WikiApi {
    private static final Gson gson = new Gson();
    @Override
    public CompletableFuture<WikiResult[]> search(String keyword) throws InterruptedException, ExecutionException {
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

        return futureResponse.thenApply((HttpResponse<String> response) -> {
            System.out.println(response.body());
            WikiResult[] wikiResults = gson.fromJson(response.body(), WikiResult[].class);
            return wikiResults;
        });
    }
}

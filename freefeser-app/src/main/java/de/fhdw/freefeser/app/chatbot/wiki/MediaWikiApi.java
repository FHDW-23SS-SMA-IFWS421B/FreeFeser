package de.fhdw.freefeser.app.chatbot.wiki;

import com.google.gson.*;
import de.fhdw.freefeser.api.util.HttpWrapper;
import de.fhdw.freefeser.api.util.JsonParser;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MediaWikiApi implements WikiApi {

    private final JsonParser jsonParser;
    private final HttpWrapper httpWrapper;

    public MediaWikiApi(JsonParser jsonParser, HttpWrapper httpWrapper) {
        this.jsonParser = jsonParser;
        this.httpWrapper = httpWrapper;
    }

    @Override
    public CompletableFuture<WikiResult[]> search(String keyword) {
        String endpoint = "https://de.wikipedia.org/w/rest.php/v1/search/";
        String params = "page?q=" + keyword + "&limit=5";
        String url = endpoint + params;

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpWrapper.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply((HttpResponse<String> response) -> {
            JsonArray wikiResults = jsonParser.fromJson(response.body(), JsonObject.class).getAsJsonArray("pages");

            List<WikiResult> results = new ArrayList<>();

            for (JsonElement jsonElement : wikiResults) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String title = jsonObject.get("title").getAsString();
                String description = jsonObject.get("description").getAsString();
                results.add(new AppWikiResult(title, description));
            }

            return results.toArray(results.toArray(new WikiResult[0]));
        }).exceptionally(throwable -> {
            throw new RuntimeException(throwable);
        });
    }
}

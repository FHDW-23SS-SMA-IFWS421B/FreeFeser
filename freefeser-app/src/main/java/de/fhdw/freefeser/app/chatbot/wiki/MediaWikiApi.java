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
    public CompletableFuture<AppWikiResult[]> search(String keyword) {
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

            List<AppWikiResult> results = new ArrayList<>();

            for (JsonElement jsonElement : wikiResults) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                JsonElement titleElement = jsonObject.get("title");
                String title = (!titleElement.isJsonNull()) ? titleElement.getAsString() : "keine Angabe";

                JsonElement descriptionElement = jsonObject.get("description");
                String description = (!descriptionElement.isJsonNull()) ? descriptionElement.getAsString() : "keine Angabe";

                results.add(new AppWikiResult(title, description));
            }

            return results.toArray(new AppWikiResult[0]);
        }).exceptionally(throwable -> {
            throw new RuntimeException(throwable);
        });
    }
}

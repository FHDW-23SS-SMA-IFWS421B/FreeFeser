package de.fhdw.freefeser.app.chatbot.translation;

import com.google.gson.JsonObject;
import de.fhdw.freefeser.api.util.HttpClientWrapper;
import de.fhdw.freefeser.api.util.JsonParser;
import de.fhdw.freefeser.api.util.YamlParser;
import de.fhdw.freefeser.app.util.Credentials;
import de.fhdw.freefeser.app.util.SnakeYamlParser;
import de.fhdw.freefeser.app.util.YamlApiCredentials;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class DeepLTranslationApi implements TranslationApi {

    private final JsonParser jsonParser;
    private final HttpClientWrapper httpClientWrapper;
    private final String apiKey;

    public DeepLTranslationApi(JsonParser jsonParser, HttpClientWrapper httpClientWrapper, YamlParser yamlParser, String filePath) {
        this.jsonParser = jsonParser;
        this.httpClientWrapper = httpClientWrapper;

        // Create and configure the YamlApiCredentials instance
        YamlApiCredentials yamlApiCredentials = new YamlApiCredentials(yamlParser, filePath, Credentials::getDeeplApiKey);
        apiKey = yamlApiCredentials.getApiKey();
    }

    @Override
    public CompletableFuture<TranslationResult> translate(String targetLanguage, String value) {
        String endpoint = "https://api-free.deepl.com/v2/translate";

        String params = "auth_key=" + apiKey + "&text=" + URLEncoder.encode(value, StandardCharsets.UTF_8) + "&target_lang=" + targetLanguage;
        String url = endpoint + "?" + params;

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClientWrapper.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> {
            JsonObject rawResult = jsonParser.fromJson(response.body(), JsonObject.class);
            JsonObject firstTranslation = rawResult.getAsJsonArray("translations").get(0).getAsJsonObject();

            String sourceLanguage = firstTranslation.get("detected_source_language").getAsString();
            String translation = firstTranslation.get("text").getAsString();

            return new AppTranslationResult(sourceLanguage, targetLanguage, translation, value);
        });
    }
}

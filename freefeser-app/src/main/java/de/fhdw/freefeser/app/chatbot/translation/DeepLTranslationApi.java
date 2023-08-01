package de.fhdw.freefeser.app.chatbot.translation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class DeepLTranslationApi implements TranslationApi {

    @Override
    public CompletableFuture<TranslationResult> translate(String targetLanguage, String value) {
        String endpoint = "https://api-free.deepl.com/v2/translate";
        String apiKey = "3f881fa8-df8f-647c-9658-5cdf3e8f85ac:fx";
        String params = "auth_key=" + apiKey + "&text=" + URLEncoder.encode(value, StandardCharsets.UTF_8) + "&target_lang=" + targetLanguage;

        String url = endpoint + "?" + params;

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> {

            Gson gson = new Gson();//@TOdo abstract
            JsonObject rawResult = gson.fromJson(response.body(), JsonObject.class);
            JsonObject firstTranslation = rawResult.getAsJsonArray("translations").get(0).getAsJsonObject();//This result is documented by DeepL api, there is always a translation

            String sourceLanguage = firstTranslation.get("detected_source_language").getAsString();
            String translation = firstTranslation.get("text").getAsString();

            return new AppTranslationResult(sourceLanguage, targetLanguage, translation, value);
        });
    }
}

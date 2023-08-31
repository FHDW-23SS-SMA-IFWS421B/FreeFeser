package de.fhdw.freefeser.app.chatbot.weather;

import com.google.gson.Gson;
import de.fhdw.freefeser.api.util.YamlParser;
import de.fhdw.freefeser.app.util.Credentials;
import de.fhdw.freefeser.app.util.YamlApiCredentials;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OpenWeatherApi implements WeatherApi {
    private final Gson gson = new Gson();
    private final String apiKey;
    protected String endpoint = "https://api.openweathermap.org/data/2.5/weather";

    public OpenWeatherApi(YamlParser yamlParser, InputStream inputStream) {
        // Create and configure the YamlApiCredentials instance
        YamlApiCredentials yamlApiCredentials = new YamlApiCredentials(yamlParser, inputStream, Credentials::getWeatherApiKey);
        apiKey = yamlApiCredentials.getApiKey();
    }

    @Override
    public CompletableFuture<WeatherResult> getCurrentWeather(String location) {
        String params = "weather?q=" + location + "&appid=" + apiKey;
        String url = endpoint + params;

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> gson.fromJson(response.body(), WeatherResult.class));
    }

    @Override
    public CompletableFuture<List<WeatherResult>> getForecastWeather(String location) {
        // TODO: pls finish the implementation of forecast and adjust the return statement etc.
        // URL for forecast:
        String params = "forecast?q=" + location + "&appid=" + apiKey + "&units=metric";
        String url = endpoint + params;

        return getCurrentWeather(location).thenApply(List::of);
    }
}

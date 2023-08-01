package de.fhdw.freefeser.app.chatbot.weather;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OpenWeatherApi implements WeatherApi {
    private final Gson gson = new Gson();

    @Override
    public CompletableFuture<WeatherResult> getCurrentWeather(String location) {
        String endpoint = "https://api.openweathermap.org/data/2.5/weather";
        String apiKey = "35ff219a2bddf423ebdca8a86cee38dd";
        String params = "?q=" + location + "&appid=" + apiKey;
        String url = endpoint + params;

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> gson.fromJson(response.body(), WeatherResult.class));
    }

    @Override
    public CompletableFuture<List<WeatherResult>> getForecastWeather(String location) {
        // The implementation for forecast weather is similar to getCurrentWeather.
        // However, OpenWeatherMap API returns a single WeatherResult for forecast, not a list.
        // For simplicity, we'll return a CompletableFuture with a list containing a single WeatherResult.
        return getCurrentWeather(location).thenApply(List::of);
    }
}

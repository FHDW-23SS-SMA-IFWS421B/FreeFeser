package de.fhdw.freefeser.app.chatbot.weather;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OpenWeatherApi implements WeatherApi {
    // maybe it's smarter to declare and initialize the vars in the methods but then we've duplicated code
    private final String endpoint = "http://api.openweathermap.org/data/2.5/weather";
    private final String apiKey = "35ff219a2bddf423ebdca8a86cee38dd";

    @Override
    public CompletableFuture<WeatherResult> getCurrentWeather(String location) throws InterruptedException, ExecutionException {
        String params = "?q=" + location + "&appid=" + apiKey;
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
            return (WeatherResult) response;
        });
    }

    @Override
    public CompletableFuture<List<WeatherResult>> getForecastWeather(String location) throws InterruptedException, ExecutionException {
        String params = "?q=" + location + "&appid=" + apiKey;
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
            return (List<WeatherResult>) response;
        });
    }
}

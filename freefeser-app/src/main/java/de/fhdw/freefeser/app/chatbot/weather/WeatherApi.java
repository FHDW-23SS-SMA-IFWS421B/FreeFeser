package de.fhdw.freefeser.app.chatbot.weather;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface WeatherApi {

    CompletableFuture<WeatherResult> getCurrentWeather(String query);

    CompletableFuture<List<WeatherResult>> getForecastWeather(String query);
}

package de.fhdw.freefeser.app.chatbot.weather;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface WeatherApi {

    CompletableFuture<WeatherResult> getCurrentWeather(String location) throws InterruptedException, ExecutionException;

    CompletableFuture<List<WeatherResult>> getForecastWeather(String location) throws InterruptedException, ExecutionException;
}

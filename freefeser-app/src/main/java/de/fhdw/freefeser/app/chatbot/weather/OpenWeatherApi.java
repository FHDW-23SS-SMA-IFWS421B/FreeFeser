package de.fhdw.freefeser.app.chatbot.weather;

import de.fhdw.freefeser.api.util.HttpWrapper;
import de.fhdw.freefeser.api.util.JsonParser;
import de.fhdw.freefeser.app.util.Credentials;
import de.fhdw.freefeser.app.util.YamlApiCredentials;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class OpenWeatherApi implements WeatherApi {
    private final JsonParser jsonParser;
    private final HttpWrapper httpWrapper;
    private final String apiKey;
    protected String endpoint = "https://api.openweathermap.org/data/2.5/";

    public OpenWeatherApi(JsonParser jsonParser, HttpWrapper httpWrapper, Credentials credentials) {
        this.jsonParser = jsonParser;
        this.httpWrapper = httpWrapper;

        // Create and configure the YamlApiCredentials instance
        YamlApiCredentials yamlApiCredentials = new YamlApiCredentials(credentials, Credentials::getWeatherApiKey);
        apiKey = yamlApiCredentials.getApiKey();
    }

    @Override
    public CompletableFuture<WeatherResult> getCurrentWeather(String location) {
        String params = "weather?q=" + location + "&lang=de" + "&appid=" + apiKey + "&units=metric";
        String url = endpoint + params;

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpWrapper.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> {
            JsonObject rawResult = jsonParser.fromJson(response.body(), JsonObject.class);
            JsonArray weatherList = rawResult.getAsJsonArray("weather");
            if(weatherList.isEmpty())
                return null;
            JsonObject weather = weatherList.get(0).getAsJsonObject();
            JsonObject main = rawResult.getAsJsonObject("main");
            JsonObject wind = rawResult.getAsJsonObject("wind");

            String date = "Aktuell";
            String weatherCondition = String.valueOf(weather.get("description"));
            String temperature = String.valueOf(main.get("temp"));
            String windspeed = String.valueOf(wind.get("speed"));
            String humidity = String.valueOf(main.get("humidity"));

            return (WeatherResult) new AppWeatherResult(date, weatherCondition,temperature, windspeed, humidity);
        }).exceptionally(throwable -> {
            throw new RuntimeException(throwable);
        });
    }

    @Override
    public CompletableFuture<List<WeatherResult>> getForecastWeather(String location) {
        String params = "forecast?q=" + location + "&lang=de" + "&appid=" + apiKey + "&units=metric";
        String url = endpoint + params;

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpWrapper.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        return futureResponse.thenApply(response -> {
            JsonArray forecastList = jsonParser.fromJson(response.body(), JsonObject.class).getAsJsonArray("list");

            List<WeatherResult> weatherResults = new ArrayList<>();

            int days = 0;
            String lastDate = null;
            for (JsonElement element : forecastList) {
                if(days == 3) {
                    break;
                }
                JsonObject forecastData = element.getAsJsonObject();
                JsonObject main = forecastData.getAsJsonObject("main");
                JsonObject weather = forecastData.getAsJsonArray("weather").get(0).getAsJsonObject();
                JsonObject wind = forecastData.getAsJsonObject("wind");

                String dateWithTime = forecastData.get("dt_txt").getAsString();

                // extract only the date
                String date = dateWithTime.split(" ")[0];

                if(!dateWithTime.contains("12:00:00")) {
                    continue;
                }

                String weatherCondition = weather.get("description").getAsString();
                String temperature = main.get("temp").getAsString();
                String windspeed = wind.get("speed").getAsString();
                String humidity = main.get("humidity").getAsString();

                WeatherResult weatherResult = new AppWeatherResult(dateWithTime, weatherCondition, temperature, windspeed, humidity);
                weatherResults.add(weatherResult);

                if(!date.equals(lastDate)) {
                    days++;
                }
                lastDate = date;
            }
            return weatherResults;
        }).exceptionally(throwable -> {
            throw new RuntimeException(throwable);
        });
    }
}

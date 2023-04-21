package de.fhdw.freefeser.app.chatbot.weather;

import java.util.List;

public interface WeatherApi {

    WeatherResult getCurrentWeather(String query);

    List<WeatherResult> getForecastWeather(String query);
}

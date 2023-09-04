package de.fhdw.freefeser.app.chatbot.weather;

public interface WeatherResult {

    String getDate();
    String getWeatherCondition();
    String getTemperature();
    String getWindspeed();
    String getHumidity();
}

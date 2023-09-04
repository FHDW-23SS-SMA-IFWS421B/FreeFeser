package de.fhdw.freefeser.app.chatbot.weather;

public class AppWeatherResult implements WeatherResult {

    private final String date;
    private final String weatherCondition;
    private final String temperature;
    private final String humidity;
    private final String windspeed;

    public AppWeatherResult(String date, String weatherCondition, String temperature, String windspeed, String humidity) {
        this.date = date;
        this.weatherCondition = weatherCondition;
        this.temperature = temperature;
        this.windspeed = windspeed;
        this.humidity = humidity;
    }

    public String getDate() {
        return this.date;
    }

    public String getWeatherCondition() {
        return this.weatherCondition;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public String getWindspeed() {
        return this.windspeed;
    }

    public String getHumidity() {
        return this.humidity;
    }
}

package de.fhdw.freefeser.app.util;

// A POJO to visualize the credentials.yaml structure
public class Credentials {

    private final String DeeplApiKey;
    private final String WeatherApiKey;

    public Credentials(String DeelApiKey, String WeatherApiKey) {
        this.DeeplApiKey = DeelApiKey;
        this.WeatherApiKey = WeatherApiKey;
    }

    public String getDeeplApiKey() {
        return DeeplApiKey;
    }

    public String getWeatherApiKey() {
        return WeatherApiKey;
    }
}

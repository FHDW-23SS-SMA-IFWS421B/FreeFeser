package de.fhdw.freefeser.app.util;

// A POJO to visualize the credentials.yaml structure
public class Credentials {

    public String deeplApiKey;
    public String weatherApiKey;

    public Credentials() {

    }

    public String getDeeplApiKey() {
        return deeplApiKey;
    }

    public String getWeatherApiKey() {
        return weatherApiKey;
    }
}

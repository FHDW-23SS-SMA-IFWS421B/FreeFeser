package de.fhdw.freefeser.api.textanalyzer;

public interface WeatherTextAnalyzer extends TextAnalyzer {

    String extractLocation(String text);

    String extractWeatherCurrentOrForecast(String text);
}

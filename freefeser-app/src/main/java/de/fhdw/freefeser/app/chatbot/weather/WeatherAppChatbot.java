package de.fhdw.freefeser.app.chatbot.weather;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.textanalyzer.WeatherTextAnalyzer;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.api.util.HttpWrapper;
import de.fhdw.freefeser.api.util.JsonParser;
import de.fhdw.freefeser.app.chatbot.AppChatbot;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;
import de.fhdw.freefeser.app.textanalyzer.AppWeatherTextAnalyzer;
import de.fhdw.freefeser.app.util.Credentials;

import java.util.HashMap;

public class WeatherAppChatbot extends AppChatbot {

    private final WeatherApi weatherApi;
    private final WeatherTextAnalyzer weatherTextAnalyzer;

    public WeatherAppChatbot(JsonParser jsonParser, HttpWrapper httpWrapper, Credentials credentials, ConsolePrinter printer, String name, UserManager userManager, AppChatMessageDatabaseManager chatMessageDatabaseManager, ChatbotEntityDatabaseManager databaseManager) {
        super(printer, name, userManager, chatMessageDatabaseManager, databaseManager);
        this.weatherApi = new OpenWeatherApi(jsonParser, httpWrapper, credentials);
        this.weatherTextAnalyzer = new AppWeatherTextAnalyzer();
    }

    @Override
    public void onExecute(User sender, String rawText) {
        try {
            HashMap<String, String> analysisResult = weatherTextAnalyzer.analyze(rawText);
            String location = analysisResult.get("Location");
            String weatherType = analysisResult.get("WeatherType");

            if(weatherType.equalsIgnoreCase("current")) {
                weatherApi.getCurrentWeather(location).thenAcceptAsync(result -> {
                    String message = result.getDate() + " verhält sich das Wetter in " + location + "wie folgt:";
                    message += "\n- Wetterlage: " + result.getWeatherCondition();
                    message += "\n- Temperatur: " + result.getTemperature() + "ºC";
                    message += "\n- Windgeschwindigkeit: " + result.getWindspeed() + " m/s";
                    message += "\n- Luftfeuchtigkeit: " + result.getHumidity() + " %";

                    sendMessageOnBehalf(message);
                });
            } else if (weatherType.equalsIgnoreCase("forecast")) {
                weatherApi.getForecastWeather(location).thenAcceptAsync(results -> {
                    StringBuilder message = new StringBuilder("3-Tages-Wettervorhersage für " + location);
                    for (WeatherResult result : results) {
                        message.append("\n" + "Datum: ").append(result.getDate());
                        message.append("\n" + "- Wetterlage: ").append(result.getWeatherCondition());
                        message.append("\n" + "- Temperatur: ").append(result.getTemperature()).append("ºC");
                        message.append("\n" + "- Windgeschwindigkeit: ").append(result.getWindspeed()).append(" m/s");
                        message.append("\n" + "- Luftfeuchtigkeit: ").append(result.getHumidity()).append(" %");
                    }
                    sendMessageOnBehalf(message.toString());
                });
            } else {
                sendErrorMessage();
            }
        } catch (Exception e) {
            sendErrorMessage();
        }
    }
}

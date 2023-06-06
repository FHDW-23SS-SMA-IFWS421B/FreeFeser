package de.fhdw.freefeser.app.prototype.textanalyzer;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;

import java.io.FileInputStream;
import java.util.List;

public class TextAnalyzer {
    public static String extractBot(String text) throws Exception {
        // Load the credentials from the config file
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream("/home/florenzen/FreeFeser/freefeser-app/src/main/resources/credentials.json"));

        // Instantiates a client
        try (LanguageServiceClient language = LanguageServiceClient.create(LanguageServiceSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build())) {
            // Set the text content and type (plain text)
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();

            // Detects entities in the document
            AnalyzeEntitiesResponse response = language.analyzeEntities(doc);
            List<Entity> entities = response.getEntitiesList();

            // Look for entities that match the bot names and return the first one found
            String[] botNames = {"Weatherbot", "Wikibot", "TranslationBot"}; // Array of bot names
            for (Entity entity : entities) {
                String entityName = entity.getName();
                for (String botName : botNames) {
                    if (entityName.equalsIgnoreCase(botName)) {
                        // return in lowercase for further use
                        return botName.toLowerCase();
                    }
                }
            }

            // If no bot entity was found, return null
            return null;
        }
    }

    public static String extractLocation(String text) throws Exception {
        // Load the credentials from the config file
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream("/home/florenzen/FreeFeser/freefeser-app/src/main/resources/credentials.json"));

        // Instantiates a client
        try (LanguageServiceClient language = LanguageServiceClient.create(LanguageServiceSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build())) {
            // Set the text content and type (plain text)
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();

            // Detects entities in the document
            AnalyzeEntitiesResponse response = language.analyzeEntities(doc);
            List<Entity> entities = response.getEntitiesList();

            // Look for entities that are locations and return the first one found
            for (Entity entity : entities) {
                if (entity.getType() == Entity.Type.LOCATION) {
                    // return in lowercase for further use
                    return entity.getName().toLowerCase();
                }
            }

            // If no location entity was found, return null
            return null;
        }
    }

    public static String extractWeatherCurrentOrForecast(String text) throws Exception {
        // Load the credentials from the config file
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream("/home/florenzen/FreeFeser/freefeser-app/src/main/resources/credentials.json"));
        // Instantiates a client
        try (LanguageServiceClient language = LanguageServiceClient.create(LanguageServiceSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build())) {
            // Set the text content and type (plain text)
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();

            // Analyze the sentiment of the document
            AnalyzeSentimentResponse sentimentResponse = language.analyzeSentiment(doc);
            Sentiment sentiment = sentimentResponse.getDocumentSentiment();

            // Check if the sentiment is positive (for current weather) or negative (for a forecast)
            if (sentiment.getScore() >= 0) {
                // Check for keywords indicating current weather
                if (text.toLowerCase().contains("heute") || text.toLowerCase().contains("jetzt")) {
                    return "current";
                }
            } else {
                // Check for keywords indicating a weather forecast
                if (text.toLowerCase().contains("morgen") || text.toLowerCase().contains("wochenende") || text.toLowerCase().contains("nächsten tagen")) {
                    return "forecast";
                }
            }

            // If no keywords were found, return null
            return null;
        }
    }
}

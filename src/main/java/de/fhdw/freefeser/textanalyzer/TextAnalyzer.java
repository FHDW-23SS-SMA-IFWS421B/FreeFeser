package de.fhdw.freefeser.textanalyzer;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.*;
import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.*;
import java.io.FileInputStream;
import java.util.List;

public class TextAnalyzer {
    public static String extractLocation(String text) throws Exception {
        // Load the credentials from the config file
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream("src/main/java/de/fhdw/freefeser/textanalyzer/credentials.json"));

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

    public static String extractBot(String text) throws Exception {
        // Load the credentials from the config file
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream("src/main/java/de/fhdw/freefeser/textanalyzer/credentials.json"));

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
}

package de.fhdw.freefeser;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.*;
import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.*;
import java.io.FileInputStream;
import java.util.List;

public class LocationExtractor {
    public static String extractLocation(String text) throws Exception {
        // Load the credentials from the config file
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream("src/main/java/de/fhdw/freefeser/credentials.json"));

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
                    return entity.getName();
                }
            }

            // If no location entity was found, return null
            return null;

            /*
            Sample code for analyzing which Bot is used
            List<Entity> entities = response.getEntities();
        for (Entity entity : entities) {
            String entityName = entity.getName().toLowerCase();
            if (entityName.equals("wetterbot")) {
                // ...
            } else if (entityName.equals("wikibot")) {
                // ...
            } else if (entityName.equals("translationbot")) {
                // ...
            }
        }
             */
        }
    }
}

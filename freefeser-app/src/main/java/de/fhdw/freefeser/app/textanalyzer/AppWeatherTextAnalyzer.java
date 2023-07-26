package de.fhdw.freefeser.app.textanalyzer;

import de.fhdw.freefeser.api.textanalyzer.WeatherTextAnalyzer;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;

import java.util.*;

public class AppWeatherTextAnalyzer implements WeatherTextAnalyzer {

    @Override
    public HashMap<String, String> analyze(String text) {
        HashMap<String, String> analysisResults = new HashMap<>();

        analysisResults.put("Location", extractLocation(text));
        analysisResults.put("WeatherType", extractWeatherCurrentOrForecast(text));

        return analysisResults;
    }

    @Override
    public String extractLocation(String text) {
        // Create a new pipeline with annotation properties for the detected language
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create a document from the text
        CoreDocument document = new CoreDocument(text);

        // Annotate the document
        pipeline.annotate(document);

        // Extract entities from the document
        List<CoreEntityMention> entities = document.entityMentions();

        // Look for recognized location entities and return the first one found
        for (CoreEntityMention entity : entities) {
            String nerTag = entity.entityType();
            if (nerTag.equals("LOCATION")) {
                // Return the location in lowercase for further use
                return entity.text().toLowerCase();
            }
        }

        // If no location entity is found, try using POS tagging
        List<CoreSentence> sentences = document.sentences();
        for (CoreSentence sentence : sentences) {
            for (CoreLabel token : sentence.tokens()) {
                String word = token.word().toLowerCase();
                String posTag = token.get(PartOfSpeechAnnotation.class);
                // Check if the token is a proper noun (NNP) or common noun (NN) and not a one-character word
                if ((posTag.equals("NNP") || posTag.equals("NN")) && word.length() > 1) {
                    // Return the location in lowercase for further use
                    return word;
                }
            }
        }

        // If still no location is found, return null
        return null;
    }

    @Override
    public String extractWeatherCurrentOrForecast(String text) {
        // Check if the text contains keywords for current weather
        if (text.toLowerCase().contains("ist") || text.toLowerCase().contains("heute") || text.toLowerCase().contains("jetzt") || text.toLowerCase().contains("aktuell")) {
            return "current";
        }

        // Check if the text contains keywords for weather forecast
        if (text.toLowerCase().contains("wird") || text.toLowerCase().contains("morgen") || text.toLowerCase().contains("wochenende") || text.toLowerCase().contains("nächsten tagen")) {
            return "forecast";
        }

        // If no keywords are found, return null
        return null;
    }
}

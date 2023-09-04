package de.fhdw.freefeser.app.textanalyzer;

import de.fhdw.freefeser.api.textanalyzer.WeatherTextAnalyzer;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

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
        // Set up Stanford NLP pipeline with the German NER annotator enabled.
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        props.setProperty("ner.model", "edu/stanford/nlp/models/ner/german.distsim.crf.ser.gz");
        props.setProperty("ner.applyFineGrained", "false");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create an annotation object with the input text.
        Annotation document = new Annotation(text);

        // Process the text through the pipeline to annotate named entities.
        pipeline.annotate(document);

        // Extract the named entities tagged as locations.
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        String location = null;

        for (CoreMap sentence : sentences) {
            for (CoreMap token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if ("LOCATION".equalsIgnoreCase(ner)) { // "LOCATION" indicates a location entity in the German model
                    location = token.get(CoreAnnotations.TextAnnotation.class);
                    break; // Stop searching after finding the first location
                }
            }
        }

        return location;
    }

    @Override
    public String extractWeatherCurrentOrForecast(String text) {
        text = text.toLowerCase();

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

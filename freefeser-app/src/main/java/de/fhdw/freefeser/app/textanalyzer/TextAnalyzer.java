package de.fhdw.freefeser.app.textanalyzer;

import com.cybozu.labs.langdetect.LangDetectException;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;

import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.Detector;

import java.util.*;

public class TextAnalyzer {

    public static String extractBot(String text) {
        // Create a new pipeline with annotation properties
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create a document from the text
        CoreDocument document = new CoreDocument(text);

        // Annotate the document
        pipeline.annotate(document);

        // Extract POS tags from the document
        List<CoreLabel> tokens = document.tokens();
        for (CoreLabel token : tokens) {
            String posTag = token.get(PartOfSpeechAnnotation.class);
            if (posTag.equals("NNP") || posTag.equals("NN")) {
                // Return the bot name in lowercase for further use
                String botName = token.word().toLowerCase();
                if (botName.equals("weatherbot") || botName.equals("wikibot") || botName.equals("translationbot")) {
                    return botName;
                }
            }
        }

        // If no bot entity is found, return null
        return null;
    }

    public static String extractLocation(String text) throws LangDetectException {
        // Load the language profiles for language detection
        try {
            ClassLoader classLoader = TextAnalyzer.class.getClassLoader();
            DetectorFactory.loadProfile(classLoader.getResource("langdetect/profiles").getPath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // Create a language detector and detect the language of the text
        Detector detector = DetectorFactory.create();
        detector.append(text);
        String detectedLanguage = detector.detect();

        // Create a new pipeline with annotation properties for the detected language
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");

        // Set the NER model based on the detected language
        if (detectedLanguage.equals("de")) {
            props.setProperty("ner.model", "edu/stanford/nlp/models/ner/german.hgc_175m_600.crf.ser.gz");
        } else if (detectedLanguage.equals("en")) {
            props.setProperty("ner.model", "edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz");
        }

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

    public static String extractWeatherCurrentOrForecast(String text) {
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

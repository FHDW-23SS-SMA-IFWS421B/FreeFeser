package de.fhdw.freefeser.app.textanalyzer;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;

import java.util.*;

public class TextAnalyzer {
    public static String extractBot(String text) {
        // Erstellen einer neuen Pipeline mit Annotationseigenschaften
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Erstellen eines Dokuments aus dem Text
        CoreDocument document = new CoreDocument(text);

        // Annotieren des Dokuments
        pipeline.annotate(document);

        // Entitäten aus dem Dokument extrahieren
        List<CoreEntityMention> entities = document.entityMentions();

        // Nach Bots in den Entitäten suchen und den ersten gefundenen zurückgeben
        String[] botNames = {"Weatherbot", "Wikibot", "TranslationBot"}; // Array der Bot-Namen
        for (CoreEntityMention entity : entities) {
            String entityName = entity.text();
            for (String botName : botNames) {
                if (entityName.equalsIgnoreCase(botName)) {
                    // Rückgabe in Kleinbuchstaben für die weitere Verwendung
                    return botName.toLowerCase();
                }
            }
        }

        // Wenn keine Bot-Entität gefunden wurde, null zurückgeben
        return null;
    }

    public static String extractLocation(String text) {
        // Erstellen einer neuen Pipeline mit Annotationseigenschaften
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Erstellen eines Dokuments aus dem Text
        CoreDocument document = new CoreDocument(text);

        // Annotieren des Dokuments
        pipeline.annotate(document);

        // Entitäten aus dem Dokument extrahieren
        List<CoreSentence> sentences = document.sentences(); // Ändern Sie hier zu CoreSentence
        for (CoreSentence sentence : sentences) { // Ändern Sie hier zu CoreSentence
            for (CoreLabel token : sentence.tokens()) { // Verwenden Sie sentence.tokens() statt sentence.get(CoreAnnotations.TokensAnnotation.class)
                String nerTag = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if (nerTag.equals("LOCATION")) {
                    // Rückgabe des Orts in Kleinbuchstaben für die weitere Verwendung
                    return token.word().toLowerCase();
                }
            }
        }

        // Wenn keine Ortsangabe gefunden wurde, null zurückgeben
        return null;
    }


    public static String extractWeatherCurrentOrForecast(String text) {
        // Erstellen einer neuen Pipeline mit Annotationseigenschaften
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Erstellen eines Dokuments aus dem Text
        CoreDocument document = new CoreDocument(text);

        // Annotieren des Dokuments
        pipeline.annotate(document);

        // Überprüfen, ob der Text Schlüsselwörter für das aktuelle Wetter enthält
        if (text.toLowerCase().contains("heute") || text.toLowerCase().contains("jetzt") || text.toLowerCase().contains("aktuell")) {
            return "current";
        }

        // Überprüfen, ob der Text Schlüsselwörter für eine Wettervorhersage enthält
        if (text.toLowerCase().contains("morgen") || text.toLowerCase().contains("wochenende") || text.toLowerCase().contains("nächsten tagen")) {
            return "forecast";
        }

        // Wenn keine Schlüsselwörter gefunden wurden, null zurückgeben
        return null;
    }
}

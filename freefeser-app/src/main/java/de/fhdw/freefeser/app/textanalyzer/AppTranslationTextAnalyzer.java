package de.fhdw.freefeser.app.textanalyzer;

import de.fhdw.freefeser.api.textanalyzer.TranslationTextAnalyzer;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class AppTranslationTextAnalyzer implements TranslationTextAnalyzer {

    @Override
    public HashMap<String, String> analyze(String text) {
        HashMap<String, String> analysisResults = new HashMap<>();
        return analysisResults;
    }

    @Override
    public String extractTextToTranslate(String text) {
        return null;
    }


    @Override
    public String extractTargetLanguage(String text) {
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

        // Look for recognized language entities and return the first one found
        for (CoreEntityMention entity : entities) {
            String nerTag = entity.entityType();
            if (nerTag.equals("LANGUAGE")) {
                // Return the detected language in lowercase for further use
                return entity.text().toLowerCase();
            }
        }

        // If no language entity is found, return null
        return null;
    }
}

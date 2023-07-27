package de.fhdw.freefeser.app.textanalyzer;

import de.fhdw.freefeser.api.textanalyzer.TextAnalyzer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class AppTextAnalyzer implements TextAnalyzer {

    @Override
    public HashMap<String, String> analyze(String text) {
        HashMap<String, String> analysisResults = new HashMap<>();

        analysisResults.put("Bot", extractBot(text));

        return analysisResults;
    }

    @Override
    public String extractBot(String text) {
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
            String posTag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
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
}

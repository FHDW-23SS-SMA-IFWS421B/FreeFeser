package de.fhdw.freefeser.app.textanalyzer;

import de.fhdw.freefeser.api.textanalyzer.WikiTextAnalyzer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class AppWikiTextAnalyzer extends AppTextAnalyzer implements WikiTextAnalyzer {

    @Override
    public HashMap<String, String> analyze(String text) {
        HashMap<String, String> analysisResults = new HashMap<>();
        analysisResults.put("SearchTerm", extractSearchTerm(text));
        analysisResults.put("Bot", extractBot(text));
        return analysisResults;
    }

    @Override
    public String extractSearchTerm(String text) {
        // Split the text into sentences
        String[] sentences = text.split(";");

        // Set of question words to ignore
        Set<String> IGNORED_QUESTION_WORDS = new HashSet<>(Arrays.asList("wer", "was"));

        // Set of German stop words to exclude
        Set<String> GERMAN_STOP_WORDS = new HashSet<>(Arrays.asList(
                "ich", "du", "er", "sie", "es", "wir", "ihr", "sie", "mich", "dich", "mir", "dir",
                "euch", "ihm", "ihn", "ihr", "uns", "denn", "aber", "doch", "ob", "als", "nicht",
                "sein", "haben", "werden", "ist", "war", "wurde", "wird"
        ));

        // Set up Stanford NLP pipeline with the German NER annotator enabled.
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        props.setProperty("ner.model", "edu/stanford/nlp/models/ner/german.distsim.crf.ser.gz");
        props.setProperty("ner.applyFineGrained", "false");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Set<String> searchTerms = new HashSet<>();

        for (String sentence : sentences) {
            // Create an annotation object with the sentence.
            Annotation document = new Annotation(sentence);

            // Process the sentence through the pipeline to annotate named entities and parts of speech.
            pipeline.annotate(document);

            // Extract the named entities tagged as persons (NER: PERSON) and locations (NER: LOCATION).
            List<CoreMap> sentencesWithEntities = document.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentenceWithEntity : sentencesWithEntities) {
                for (CoreMap token : sentenceWithEntity.get(CoreAnnotations.TokensAnnotation.class)) {
                    String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                    if ("PERSON".equalsIgnoreCase(ner) || "LOCATION".equalsIgnoreCase(ner)) { // Include "PERSON" and "LOCATION" named entities
                        searchTerms.add(token.get(CoreAnnotations.TextAnnotation.class));
                    }
                }
            }

            // Extract all nouns and filter out unwanted tokens and stop words.
            for (CoreMap sentenceWithEntity : sentencesWithEntities) {
                for (CoreMap token : sentenceWithEntity.get(CoreAnnotations.TokensAnnotation.class)) {
                    String posTag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                    String word = token.get(CoreAnnotations.TextAnnotation.class).toLowerCase();
                    if (posTag.startsWith("NN") && !IGNORED_QUESTION_WORDS.contains(word) && !GERMAN_STOP_WORDS.contains(word)) {
                        searchTerms.add(token.get(CoreAnnotations.TextAnnotation.class));
                    }
                }
            }
        }

        // Combine all search terms into a single string and return.
        return String.join("_", searchTerms).toUpperCase();
    }
}

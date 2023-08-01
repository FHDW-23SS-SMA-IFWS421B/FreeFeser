package de.fhdw.freefeser.app.textanalyzer;

import de.fhdw.freefeser.api.textanalyzer.TranslationTextAnalyzer;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppTranslationTextAnalyzer extends AppTextAnalyzer implements TranslationTextAnalyzer {

    @Override
    public HashMap<String, String> analyze(String text) {
        HashMap<String, String> analysisResults = new HashMap<>();

        analysisResults.put("TranslationText", extractTextToTranslate(text));
        analysisResults.put("TargetLanguage", extractTargetLanguage(text));

        return analysisResults;
    }

    @Override
    public String extractTextToTranslate(String text) {
        // Regular expression pattern to extract the text to translate.
        // Assumes the format "Übersetze {direction} {targetLanguage}: {textToTranslate}".
        // Example: "Übersetze ins DE: Ich love ducks."
        //          "Übersetze auf ENG: Hallo"
        String pattern = "Übersetze (ins|auf) (\\w+):\\s(.*)";
        java.util.regex.Pattern regexPattern = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher matcher = regexPattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(3);
        }

        // Return null if the text to translate cannot be extracted.
        return null;
    }


    @Override
    public String extractTargetLanguage(String text) {
        // Regular expression pattern to extract the target language code.
        // Assumes the format "Übersetze {direction} {targetLanguage}: ".
        // Example: "Übersetze ins DE: Ich love ducks."
        //          "Übersetze auf ENG: Hallo"
        String pattern = "Übersetze (ins|auf) (\\w+):";
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(2);
        }

        // Return null if the target language code cannot be extracted.
        return null;
    }
}

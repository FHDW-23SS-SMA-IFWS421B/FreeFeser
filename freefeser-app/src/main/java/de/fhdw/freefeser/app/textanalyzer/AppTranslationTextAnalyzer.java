package de.fhdw.freefeser.app.textanalyzer;

import de.fhdw.freefeser.api.textanalyzer.TranslationTextAnalyzer;

import java.util.HashMap;

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
        return null;
    }
}

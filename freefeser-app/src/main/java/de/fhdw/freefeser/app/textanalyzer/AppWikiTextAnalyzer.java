package de.fhdw.freefeser.app.textanalyzer;

import de.fhdw.freefeser.api.textanalyzer.WikiTextAnalyzer;

import java.util.HashMap;

public class AppWikiTextAnalyzer implements WikiTextAnalyzer {

    @Override
    public HashMap<String, String> analyze(String text) {
        HashMap<String, String> analysisResults = new HashMap<>();
        return analysisResults;
    }

    @Override
    public String extractSearchTerm(String text) {
        return null;
    }
}

package de.fhdw.freefeser.api.textanalyzer;

public interface WikiTextAnalyzer extends TextAnalyzer {

    String extractSearchTerm(String text);
}

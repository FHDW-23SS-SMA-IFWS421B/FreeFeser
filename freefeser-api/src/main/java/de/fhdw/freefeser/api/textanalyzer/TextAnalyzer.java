package de.fhdw.freefeser.api.textanalyzer;

import java.util.HashMap;

public interface TextAnalyzer {
    HashMap<String, String> analyze(String text);

    String extractBot(String text);
}

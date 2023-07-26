package de.fhdw.freefeser.api.textanalyzer;

public interface TranslationTextAnalyzer extends TextAnalyzer {

    String extractTextToTranslate(String text);

    String extractTargetLanguage(String text);

}

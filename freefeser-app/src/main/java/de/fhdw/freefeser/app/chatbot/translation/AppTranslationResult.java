package de.fhdw.freefeser.app.chatbot.translation;

public class AppTranslationResult implements TranslationResult {

    private final String sourceLanguage;
    private final String targetLanguage;
    private final String translation;
    private final String text;

    public AppTranslationResult(String sourceLanguage, String targetLanguage, String translation, String text) {
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.translation = translation;
        this.text = text;
    }

    @Override
    public String getSourceLanguage() {
        return this.sourceLanguage;
    }

    @Override
    public String getTargetLanguage() {
        return this.targetLanguage;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getTranslation() {
        return this.translation;
    }
}

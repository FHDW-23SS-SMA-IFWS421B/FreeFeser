package de.fhdw.freefeser.app.chatbot.translation;

public interface TranslationResult {

    String getSourceLanguage();

    String getTargetLanguage();

    String getText();

    String getTranslation();
}

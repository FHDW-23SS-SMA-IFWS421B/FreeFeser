package de.fhdw.freefeser.app.chatbot.translation;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.textanalyzer.TranslationTextAnalyzer;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.chatbot.AppChatbot;
import de.fhdw.freefeser.app.textanalyzer.AppTranslationTextAnalyzer;

import java.util.HashMap;

public class TranslationAppChatbot extends AppChatbot {

    private final TranslationApi translationApi;
    private final TranslationTextAnalyzer translationTextAnalyzer;

    public TranslationAppChatbot(ConsolePrinter printer) {
        super(printer, "translationbot");
        this.translationApi = new DeepLTranslationApi();
        this.translationTextAnalyzer = new AppTranslationTextAnalyzer();
    }

    @Override
    public void onExecute(User sender, String rawText) {
        HashMap<String, String> analysisResult = translationTextAnalyzer.analyze(rawText);
        String targetLanguage = analysisResult.get("TargetLanguage");
        String translationText = analysisResult.get("TranslationText");

        translationApi.translate(targetLanguage.toLowerCase(), translationText).thenAccept(result -> sendMessageOnBehalf("Die Übersetzung ist: " + result.getTranslation()));
    }
}

package de.fhdw.freefeser.app.chatbot.translation;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.textanalyzer.TextAnalyzer;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.chatbot.AppChatbot;
import de.fhdw.freefeser.app.textanalyzer.AppTranslationTextAnalyzer;

import java.util.HashMap;

public class TranslationAppChatbot extends AppChatbot {

    private final TranslationApi translationApi;
    private final TextAnalyzer textAnalyzer;

    public TranslationAppChatbot(ConsolePrinter printer) {
        super(printer, "translationbot");
        this.translationApi = new DeepLTranslationApi();
        this.textAnalyzer = new AppTranslationTextAnalyzer();
    }

    @Override
    public void onExecute(User sender, String rawText) {
        HashMap<String, String> analyzeResult = textAnalyzer.analyze(rawText);
        String targetLanguage = analyzeResult.get("TargetLanguage");
        String translationText = analyzeResult.get("TranslationText");

        translationApi.translate(targetLanguage.toLowerCase(), translationText).thenAccept(result -> sendMessageOnBehalf("Die Übersetzung ist: " + result.getTranslation()));
    }
}

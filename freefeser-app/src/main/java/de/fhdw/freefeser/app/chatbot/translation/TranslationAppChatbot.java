package de.fhdw.freefeser.app.chatbot.translation;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.textanalyzer.TranslationTextAnalyzer;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.api.util.HttpWrapper;
import de.fhdw.freefeser.api.util.JsonParser;
import de.fhdw.freefeser.api.util.YamlParser;
import de.fhdw.freefeser.app.chatbot.AppChatbot;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;
import de.fhdw.freefeser.app.textanalyzer.AppTranslationTextAnalyzer;

import java.util.HashMap;

public class TranslationAppChatbot extends AppChatbot {

    private final TranslationApi translationApi;
    private final TranslationTextAnalyzer translationTextAnalyzer;

    public TranslationAppChatbot(ConsolePrinter printer, String name, UserManager userManager, AppChatMessageDatabaseManager chatMessageDatabaseManager, JsonParser jsonParser, HttpWrapper httpWrapper, YamlParser yamlParser, String filePath, ChatbotEntityDatabaseManager databaseManager) {
        super(printer, name, userManager, chatMessageDatabaseManager, databaseManager);
        this.translationApi = new DeepLTranslationApi(jsonParser, httpWrapper, yamlParser, filePath);
        this.translationTextAnalyzer = new AppTranslationTextAnalyzer();
    }

    @Override
    public void onExecute(User sender, String rawText) {
        HashMap<String, String> analysisResult = translationTextAnalyzer.analyze(rawText);
        String targetLanguage = analysisResult.get("TargetLanguage");
        String translationText = analysisResult.get("TranslationText");

        translationApi.translate(targetLanguage.toLowerCase(), translationText)
                .thenAccept(result -> sendMessageOnBehalf(
                        "Die Übersetzung ist: " + result.getTranslation()));
    }
}

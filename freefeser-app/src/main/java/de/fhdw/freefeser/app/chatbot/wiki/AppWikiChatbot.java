package de.fhdw.freefeser.app.chatbot.wiki;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.textanalyzer.WikiTextAnalyzer;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.api.util.HttpWrapper;
import de.fhdw.freefeser.api.util.JsonParser;
import de.fhdw.freefeser.app.chatbot.AppChatbot;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;
import de.fhdw.freefeser.app.textanalyzer.AppWikiTextAnalyzer;

import java.util.HashMap;

public class AppWikiChatbot extends AppChatbot {

    private final WikiApi wikiApi;
    private final WikiTextAnalyzer wikiTextAnalyzer;

    public AppWikiChatbot(JsonParser jsonParser, HttpWrapper httpWrapper, ConsolePrinter printer, String name, UserManager userManager, AppChatMessageDatabaseManager chatMessageDatabaseManager, ChatbotEntityDatabaseManager databaseManager) {
        super(printer, name, userManager, chatMessageDatabaseManager, databaseManager);
        this.wikiApi = new WikipediaWikiApi(jsonParser, httpWrapper);
        this.wikiTextAnalyzer = new AppWikiTextAnalyzer();
    }

    @Override
    public void onExecute(User sender, String rawText) {
        try {
            HashMap<String, String> analysisResult = wikiTextAnalyzer.analyze(rawText);
            String keyword = analysisResult.get("SearchTerm");

            wikiApi.search(keyword).thenAccept(results -> {
                StringBuilder message = new StringBuilder("Folgende Informationen habe ich zu " + transformKeyword(keyword) + " gefunden:");
                for (WikiResult result : results) {
                    message.append("\n- ").append(result.getTitle()).append(": ").append(result.getDescription());
                }
                sendMessageOnBehalf(message.toString());
            });
        } catch (Exception e) {
            sendErrorMessage();
        }
    }

    private String transformKeyword(String keyword) {
        keyword = keyword.replace("_", " ");
        String[] parts = keyword.split(" ");
        StringBuilder transformedKeyword = new StringBuilder();
        for (String part : parts) {
            if (transformedKeyword.length() > 0) {
                transformedKeyword.append(" ");
            }
            transformedKeyword.append(part.substring(0, 1).toUpperCase());
            transformedKeyword.append(part.substring(1).toLowerCase());
        }

        return transformedKeyword.toString();
    }
}

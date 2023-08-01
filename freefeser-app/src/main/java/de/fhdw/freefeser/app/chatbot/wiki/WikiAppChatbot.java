package de.fhdw.freefeser.app.chatbot.wiki;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.textanalyzer.WikiTextAnalyzer;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.chatbot.AppChatbot;
import de.fhdw.freefeser.app.textanalyzer.AppWikiTextAnalyzer;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class WikiAppChatbot extends AppChatbot {

    private final WikiApi wikiApi;
    private final WikiTextAnalyzer wikiTextAnalyzer;

    public WikiAppChatbot(ConsolePrinter printer) {
        super(printer, "wikibot");
        this.wikiApi = new MediaWikiApi();
        this.wikiTextAnalyzer = new AppWikiTextAnalyzer();
    }

    @Override
    public void onExecute(User sender, String rawText) {
        HashMap<String, String> analysisResult = wikiTextAnalyzer.analyze(rawText);
        String keyword = analysisResult.get("SearchTerm");

        // should we try catch here?
        try {
            wikiApi.search(keyword);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

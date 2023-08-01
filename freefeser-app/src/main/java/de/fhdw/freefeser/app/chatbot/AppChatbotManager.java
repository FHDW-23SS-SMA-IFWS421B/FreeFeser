package de.fhdw.freefeser.app.chatbot;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.textanalyzer.AppTranslationTextAnalyzer;
import de.fhdw.freefeser.app.chatbot.translation.TranslationAppChatbot;
import de.fhdw.freefeser.app.chatbot.weather.WeatherAppChatbot;
import de.fhdw.freefeser.app.chatbot.wiki.WikiAppChatbot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AppChatbotManager implements ChatbotManager {

    private final Collection<Chatbot> bots;
    private final TranslationAppChatbot translationAppChatbot;
    private final WeatherAppChatbot weatherAppChatbot;
    private final WikiAppChatbot wikiAppChatbot;

    public AppChatbotManager(ConsolePrinter printer) {
        this.bots = new ArrayList<>();
        this.translationAppChatbot = new TranslationAppChatbot(printer);
        this.weatherAppChatbot = new WeatherAppChatbot(printer);
        this.wikiAppChatbot = new WikiAppChatbot(printer);
    }


    @Override
    public Collection<Chatbot> getBots() {
        return this.bots;
    }

    @Override
    public boolean registerBot(Chatbot chatbot) {
        return this.bots.add(chatbot);
    }

    @Override
    public boolean unregisterBot(Chatbot chatbot) {
        return this.bots.remove(chatbot);
    }

    @Override
    public Chatbot executeCommand(User sender, String text) {
        String[] commandCheckTextRaw = text.split(" ", 2);
        if(commandCheckTextRaw.length != 2) {
            return null;
        }

        Chatbot extractedBot = extractBot(commandCheckTextRaw[0]);
        if(extractedBot == null) {
            return null;
        }

        extractedBot.onExecute(sender, commandCheckTextRaw[1]);

        return extractedBot;
    }

    public Chatbot extractBot(String firstPart) {
        if (!firstPart.startsWith("@")) {
            return null;
        }

        String botName = firstPart.substring(1);

        // take one textanalyzer instance that inherits the abstract class to call the extractBot()
        AppTranslationTextAnalyzer textAnalyzer = new AppTranslationTextAnalyzer();
        HashMap<String, String> selectedBot = textAnalyzer.analyze(botName);

        String bot = selectedBot.get("Bot");
        if (bot == null) {
            return null;
        }

        Chatbot selectedChatbot;
        switch (bot) {
            case "translationbot":
                selectedChatbot = translationAppChatbot;
                break;
            case "weatherbot":
                selectedChatbot = weatherAppChatbot;
                break;
            case "wikibot":
                selectedChatbot = wikiAppChatbot;
                break;
            default:
                return null; // Invalid bot selected
        }

        registerBot(selectedChatbot); // Register the selected chatbot
        return selectedChatbot;
    }
}

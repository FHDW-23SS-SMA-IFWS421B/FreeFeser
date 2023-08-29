package de.fhdw.freefeser.app.chatbot;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.util.HttpWrapper;
import de.fhdw.freefeser.api.util.JsonParser;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.util.YamlParser;
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

    public AppChatbotManager(ConsolePrinter printer, JsonParser jsonParser, HttpWrapper httpWrapper, YamlParser yamlParser, String filePath) {
        this.bots = new ArrayList<>();
        this.translationAppChatbot = new TranslationAppChatbot(printer, jsonParser, httpWrapper, yamlParser, filePath);
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

        extractedBot.onExecute(sender, text.substring(1));

        return extractedBot;
    }

    public Chatbot extractBot(String firstPart) {
        if (!firstPart.startsWith("@")) {
            return null;
        }

        String botName = firstPart.substring(1);

        // take one textanalyzer instance that inherits the abstract class to call the extractBot()
        AppTranslationTextAnalyzer textAnalyzer = new AppTranslationTextAnalyzer();
        HashMap<String, String> extractedBot = textAnalyzer.analyze(botName);

        String selectedBot = extractedBot.get("Bot");
        if (selectedBot == null) {
            return null;
        }

        for (Chatbot bot : bots) {
            if (bot.getName().equalsIgnoreCase(selectedBot)) {
                if (bot instanceof TranslationAppChatbot) {
                    return translationAppChatbot;
                } else if (bot instanceof WeatherAppChatbot) {
                    return weatherAppChatbot;
                } else if (bot instanceof WikiAppChatbot) {
                    return wikiAppChatbot;
                }
            }
        }

        return null;
    }
}

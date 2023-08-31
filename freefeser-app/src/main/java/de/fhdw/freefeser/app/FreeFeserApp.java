package de.fhdw.freefeser.app;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.database.UserEntityDatabaseManager;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.api.util.HttpWrapper;
import de.fhdw.freefeser.api.util.JsonParser;
import de.fhdw.freefeser.api.util.YamlParser;
import de.fhdw.freefeser.app.chatbot.AppChatbotManager;
import de.fhdw.freefeser.app.chatbot.translation.TranslationAppChatbot;
import de.fhdw.freefeser.app.chatbot.weather.WeatherAppChatbot;
import de.fhdw.freefeser.app.chatbot.wiki.WikiAppChatbot;
import de.fhdw.freefeser.app.console.printer.AppConsolePrinter;
import de.fhdw.freefeser.app.console.reader.AppConsoleReader;
import de.fhdw.freefeser.app.console.reader.callbacks.LoginConsoleReaderCallback;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;
import de.fhdw.freefeser.app.databases.managers.AppChatbotDatabaseManager;
import de.fhdw.freefeser.app.databases.managers.AppUserDatabaseManager;
import de.fhdw.freefeser.app.user.AppUserManager;
import de.fhdw.freefeser.app.util.GsonJsonParser;
import de.fhdw.freefeser.app.util.JavaHttpWrapper;
import de.fhdw.freefeser.app.util.SnakeYamlParser;

import java.io.InputStream;

public class FreeFeserApp {
    public static void main(String[] args) throws Exception {
        String filePath = "./credentials.yaml";
        InputStream inputStream = loadConfig(filePath);

        ConsolePrinter printer = new AppConsolePrinter();
        ConsoleReader reader = new AppConsoleReader(System.in);
        JsonParser jsonParser = new GsonJsonParser();
        YamlParser yamlParser = new SnakeYamlParser();
        HttpWrapper httpWrapper = new JavaHttpWrapper();

        ChatbotEntityDatabaseManager chatbotEntityDatabaseManager = new AppChatbotDatabaseManager();
        ChatbotManager chatbotManager = new AppChatbotManager(chatbotEntityDatabaseManager);

        UserEntityDatabaseManager userEntityDatabaseManager = new AppUserDatabaseManager();
        AppChatMessageDatabaseManager chatMessageDatabaseManager = new AppChatMessageDatabaseManager();

        UserManager userManager = new AppUserManager(userEntityDatabaseManager, chatbotManager, chatMessageDatabaseManager, printer, reader);
        reader.addCallback(new LoginConsoleReaderCallback(reader, printer, userManager));

        Chatbot translationBot = new TranslationAppChatbot(printer, "translationbot", userManager, chatMessageDatabaseManager, jsonParser, httpWrapper, yamlParser, inputStream, chatbotEntityDatabaseManager);
        Chatbot weatherBot = new WeatherAppChatbot(printer, "weatherbot", userManager, chatMessageDatabaseManager, chatbotEntityDatabaseManager);
        Chatbot wikiBot = new WikiAppChatbot(printer, "wikibot", userManager, chatMessageDatabaseManager, chatbotEntityDatabaseManager);
        chatbotManager.registerBot(translationBot);
        chatbotManager.registerBot(weatherBot);
        chatbotManager.registerBot(wikiBot);

        // in der Main Auswechseln der GUI oder Bots etc ermöglichen
        // Chatbot
        // Register //@Todo ask for impl for new bot

        reader.start();
    }

    private static InputStream loadConfig(String resourcePath) {
        ClassLoader classLoader = FreeFeserApp.class.getClassLoader();

        return classLoader.getResourceAsStream(resourcePath);
    }
}
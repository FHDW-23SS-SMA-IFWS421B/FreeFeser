package de.fhdw.freefeser.app;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.database.UserEntityDatabaseManager;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.api.util.HttpWrapper;
import de.fhdw.freefeser.api.util.JsonParser;
import de.fhdw.freefeser.api.util.YamlParser;
import de.fhdw.freefeser.app.chatbot.AppChatbotManager;
import de.fhdw.freefeser.app.chatbot.translation.AppTranslationChatbot;
import de.fhdw.freefeser.app.chatbot.weather.AppWeatherChatbot;
import de.fhdw.freefeser.app.chatbot.wiki.AppWikiChatbot;
import de.fhdw.freefeser.app.console.printer.AppConsolePrinter;
import de.fhdw.freefeser.app.console.reader.AppConsoleReader;
import de.fhdw.freefeser.app.console.reader.callbacks.LoginConsoleReaderCallback;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;
import de.fhdw.freefeser.app.databases.managers.AppChatbotDatabaseManager;
import de.fhdw.freefeser.app.databases.managers.AppUserDatabaseManager;
import de.fhdw.freefeser.app.user.AppUserManager;
import de.fhdw.freefeser.app.util.*;

import java.io.InputStream;

public class FreeFeserApp {
    public static void main(String[] args) throws Exception {
        String filePath = "credentials.yaml";
        InputStream inputStream = loadConfig(filePath);
        HibernateUtil hibernateUtil = new HibernateUtil();

        AppConsolePrinter printer = new AppConsolePrinter();
        AppConsoleReader reader = new AppConsoleReader(System.in);
        JsonParser jsonParser = new GsonJsonParser();
        YamlParser yamlParser = new SnakeYamlParser();
        HttpWrapper httpWrapper = new JavaHttpWrapper();

        ChatbotEntityDatabaseManager chatbotEntityDatabaseManager = new AppChatbotDatabaseManager(hibernateUtil);
        ChatbotManager chatbotManager = new AppChatbotManager(chatbotEntityDatabaseManager, printer);

        UserEntityDatabaseManager userEntityDatabaseManager = new AppUserDatabaseManager(hibernateUtil);
        AppChatMessageDatabaseManager chatMessageDatabaseManager = new AppChatMessageDatabaseManager(hibernateUtil);
        printer.setChatMessageDatabaseManager(chatMessageDatabaseManager);

        UserManager userManager = new AppUserManager(userEntityDatabaseManager, chatbotManager, chatMessageDatabaseManager, printer, reader);
        printer.setUserManager(userManager);
        reader.addCallback(new LoginConsoleReaderCallback(reader, printer, userManager));

        Credentials credentials = yamlParser.load(inputStream, Credentials.class);

        Chatbot translationBot = new AppTranslationChatbot(printer, "translationbot", userManager, chatMessageDatabaseManager, jsonParser, httpWrapper, credentials, chatbotEntityDatabaseManager);
        Chatbot weatherBot = new AppWeatherChatbot(jsonParser, httpWrapper, credentials, printer, "weatherbot", userManager, chatMessageDatabaseManager, chatbotEntityDatabaseManager);
        Chatbot wikiBot = new AppWikiChatbot(jsonParser, httpWrapper, printer, "wikibot", userManager, chatMessageDatabaseManager, chatbotEntityDatabaseManager);
        chatbotManager.registerBot(translationBot).thenAccept(complete1 -> chatbotManager.registerBot(weatherBot).thenAccept(complete2 -> chatbotManager.registerBot(wikiBot)));

        reader.start();
    }

    private static InputStream loadConfig(String resourcePath) {
        ClassLoader classLoader = FreeFeserApp.class.getClassLoader();

        return classLoader.getResourceAsStream(resourcePath);
    }
}
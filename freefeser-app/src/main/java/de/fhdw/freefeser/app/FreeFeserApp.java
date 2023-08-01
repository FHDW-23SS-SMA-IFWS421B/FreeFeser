package de.fhdw.freefeser.app;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.console.reader.ConsoleReader;
import de.fhdw.freefeser.api.database.UserEntityDatabaseManager;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.chatbot.AppChatbotManager;
import de.fhdw.freefeser.app.chatbot.translation.TranslationAppChatbot;
import de.fhdw.freefeser.app.chatbot.weather.WeatherAppChatbot;
import de.fhdw.freefeser.app.chatbot.wiki.WikiAppChatbot;
import de.fhdw.freefeser.app.console.printer.AppConsolePrinter;
import de.fhdw.freefeser.app.console.reader.AppConsoleReader;
import de.fhdw.freefeser.app.console.reader.callbacks.ChatbotManagerConsoleReaderCallback;
import de.fhdw.freefeser.app.console.reader.callbacks.LoginConsoleReaderCallback;
import de.fhdw.freefeser.app.databases.managers.AppUserDatabaseManager;
import de.fhdw.freefeser.app.user.AppUserManager;

import java.util.HashMap;

public class FreeFeserApp {
    public static void main(String[] args) throws Exception {
        ConsolePrinter printer = new AppConsolePrinter();
        ConsoleReader reader = new AppConsoleReader(System.in);

        ChatbotManager chatbotManager = new AppChatbotManager(printer);

        UserEntityDatabaseManager userEntityDatabaseManager = new AppUserDatabaseManager();

        UserManager userManager = new AppUserManager(userEntityDatabaseManager, printer, reader, userManagerInstance -> {
            reader.addCallback(new ChatbotManagerConsoleReaderCallback(reader, chatbotManager, userManagerInstance));
        });
        reader.addCallback(new LoginConsoleReaderCallback(reader, printer, userManager));

        Chatbot translationBot = new TranslationAppChatbot(printer);
        Chatbot weatherBot = new WeatherAppChatbot(printer);
        Chatbot wikiBot = new WikiAppChatbot(printer);
        chatbotManager.registerBot(translationBot);
        chatbotManager.registerBot(weatherBot);
        chatbotManager.registerBot(wikiBot);

        //Chatot
        //Register //@Todo ask for impl for new bot

        reader.start();
    }
}
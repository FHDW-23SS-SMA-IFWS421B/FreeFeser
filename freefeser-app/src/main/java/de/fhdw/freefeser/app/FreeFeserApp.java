package de.fhdw.freefeser.app;

import de.fhdw.freefeser.api.database.ChatMessage;
import de.fhdw.freefeser.api.database.User;
import de.fhdw.freefeser.app.databases.entities.AppChatbot;
import de.fhdw.freefeser.app.databases.entities.AppUser;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageManager;
import de.fhdw.freefeser.app.databases.managers.AppUserManager;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import de.fhdw.freefeser.app.textanalyzer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreeFeserApp {
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(FreeFeserApp.class);

        /*String text = "Wie ist das Wetter in New York?";
        String text2 = "Wie ist das Wetter in Bielefeld?";
        String text3 = "wikiBot";
        String text4 = "WeatherBot";
        String text5 = "Wie ist das Wetter in Berlin?";
        String text6 = "Wie wird das Wetter in Bielefeld morgen?";

        String location = WeatherTextAnalyzer.extractLocation(text);
        String location2 = WeatherTextAnalyzer.extractLocation(text2);
        logger.info("Location 1: {}", location);
        logger.info("Location 2: {}", location2);

        String bot = AppTextAnalyzer.extractBot(text3);
        String bot2 = AppTextAnalyzer.extractBot(text4);
        logger.info("Bot 1: {}", bot);
        logger.info("Bot 2: {}", bot2);

        String weather = WeatherTextAnalyzer.extractWeatherCurrentOrForecast(text5);
        String weather2 = WeatherTextAnalyzer.extractWeatherCurrentOrForecast(text6);
        logger.info("Weather 1: {}", weather);
        logger.info("Weather 2: {}", weather2);*/

        /*CommandManager commandManager = new AppCommandManager();
        commandManager.registerCommand(new TranslationCommand(null, "translate", "Translate something"));

        ConsoleReader reader = new AppConsoleReader(System.in);

        ConsoleReaderCallback loginCallback = new LoginConsoleReaderCallback(reader, user -> {
            reader.addCallback(new CommandManagerConsoleReaderCallback(reader, commandManager));
        });

        reader.addCallback(loginCallback);

        reader.start();*/

        // Save some test data to the database
        AppUserManager userManager = new AppUserManager();
        AppChatMessageManager chatMessageManager = new AppChatMessageManager();

        // Save some test data to the database
        //AppUser user1 = new AppUser("user1", "password1");
        //AppUser user2 = new AppUser("user2", "password2");
        //userManager.create(user1);
        //userManager.create(user2);

        //AppChatMessage chatMessage1 = new AppChatMessage("Hello", LocalDateTime.now(), user1, null);
        //AppChatMessage chatMessage2 = new AppChatMessage("Hi there", LocalDateTime.now(), user2, null);
        //chatMessageManager.create(chatMessage1);
        //chatMessageManager.create(chatMessage2);

        // Test getAll() for AppUser
        CompletableFuture<List<User>> allUsersFuture = userManager.getAll();
        List<User> allUsers;
        try {
            allUsers = allUsersFuture.get();
            for (User user : allUsers) {
                logger.info("User ID: {}", user.getId());
                logger.info("Username: {}", user.getUsername());
                logger.info("Password: {}", user.getPassword());
                logger.info(""); // Blank line to separate users
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Test getAll() for AppChatMessage
        CompletableFuture<List<ChatMessage<AppUser, AppChatbot>>> allChatMessagesFuture = chatMessageManager.getAll();
        List<ChatMessage<AppUser, AppChatbot>> allChatMessages;
        try {
            allChatMessages = allChatMessagesFuture.get();
            for (ChatMessage<AppUser, AppChatbot> chatMessage : allChatMessages) {
                logger.info("ChatMessage ID: {}", chatMessage.getId());
                logger.info("Text: {}", chatMessage.getText());
                logger.info("Timestamp: {}", chatMessage.getTimestamp());

                // Get the associated user and log its details
                AppUser user = chatMessage.getUser();
                logger.info("User ID: {}", user.getId());
                logger.info("Username: {}", user.getUsername());
                logger.info("Password: {}", user.getPassword());

                // Get the associated chatbot (if any) and log its details
                AppChatbot chatbot = chatMessage.getChatbot();
                if (chatbot != null) {
                    logger.info("Chatbot ID: {}", chatbot.getId());
                    logger.info("Botname: {}", chatbot.getBotname());
                    logger.info("Status: {}", chatbot.getStatus());
                }

                logger.info(""); // Blank line to separate chat messages
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
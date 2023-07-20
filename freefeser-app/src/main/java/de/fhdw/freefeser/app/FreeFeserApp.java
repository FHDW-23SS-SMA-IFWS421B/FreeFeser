package de.fhdw.freefeser.app;

import de.fhdw.freefeser.api.bot.command.CommandManager;
import de.fhdw.freefeser.api.console.ConsoleReader;
import de.fhdw.freefeser.api.console.ConsoleReaderCallback;
import de.fhdw.freefeser.api.database.ChatMessage;
import de.fhdw.freefeser.api.database.User;
import de.fhdw.freefeser.app.bot.command.AppCommandManager;
import de.fhdw.freefeser.app.chatbot.translation.commands.TranslationCommand;
import de.fhdw.freefeser.app.console.AppConsoleReader;
import de.fhdw.freefeser.app.console.callbacks.CommandManagerConsoleReaderCallback;
import de.fhdw.freefeser.app.console.callbacks.LoginConsoleReaderCallback;
import de.fhdw.freefeser.app.databases.entities.AppChatMessage;
import de.fhdw.freefeser.app.databases.entities.AppChatbot;
import de.fhdw.freefeser.app.databases.entities.AppUser;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageManager;
import de.fhdw.freefeser.app.databases.managers.AppUserManager;
import de.fhdw.freefeser.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import de.fhdw.freefeser.app.textanalyzer.TextAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreeFeserApp {
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(FreeFeserApp.class);

        /*String text = "Wie ist das Wetter in Berlin?";
        String text2 = "Wie ist das Wetter in deiner Mutter?";
        String text3 = "wikiBot";
        String text4 = "WeatherBot";
        String text5 = "Wie ist das Wetter in Berlin?";
        String text6 = "Wie wird das Wetter in Bielefeld morgen?";

        String location = TextAnalyzer.extractLocation(text);
        String location2 = TextAnalyzer.extractLocation(text2);
        System.out.println(location + "\n" + location2 + "\n");

        String bot = TextAnalyzer.extractBot(text3);
        String bot2 = TextAnalyzer.extractBot(text4);
        System.out.println(bot + "\n" + bot2 + "\n");

        String weather = TextAnalyzer.extractWeatherCurrentOrForecast(text5);
        String weather2 = TextAnalyzer.extractWeatherCurrentOrForecast(text6);
        System.out.println(weather + "\n" + weather2);

        CommandManager commandManager = new AppCommandManager();
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
        /*AppUser user1 = new AppUser("user1", "password1");
        AppUser user2 = new AppUser("user2", "password2");
        userManager.create(user1);
        userManager.create(user2);

        AppChatMessage chatMessage1 = new AppChatMessage("Hello", LocalDateTime.now(), user1, null);
        AppChatMessage chatMessage2 = new AppChatMessage("Hi there", LocalDateTime.now(), user2, null);
        chatMessageManager.create(chatMessage1);
        chatMessageManager.create(chatMessage2);*/

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
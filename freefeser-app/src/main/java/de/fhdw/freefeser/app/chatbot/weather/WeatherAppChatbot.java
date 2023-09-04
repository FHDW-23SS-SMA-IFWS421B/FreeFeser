package de.fhdw.freefeser.app.chatbot.weather;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.database.ChatbotEntityDatabaseManager;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;
import de.fhdw.freefeser.app.chatbot.AppChatbot;
import de.fhdw.freefeser.app.databases.managers.AppChatMessageDatabaseManager;

public class WeatherAppChatbot extends AppChatbot {

    public WeatherAppChatbot(ConsolePrinter printer, String name, UserManager userManager, AppChatMessageDatabaseManager chatMessageDatabaseManager, ChatbotEntityDatabaseManager databaseManager) {
        super(printer, name, userManager, chatMessageDatabaseManager, databaseManager);
    }

    @Override
    public void onExecute(User sender, String rawText) {
        try {
            String a = null;
        } catch (Exception e) {
            sendErrorMessage();
        }
    }
}

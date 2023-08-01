package de.fhdw.freefeser.app.chatbot.weather;

import de.fhdw.freefeser.api.console.printer.ConsolePrinter;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.app.chatbot.AppChatbot;

public class WeatherAppChatbot extends AppChatbot {

    public WeatherAppChatbot(ConsolePrinter printer) {
        super(printer, "weatherbot");
    }

    @Override
    public void onExecute(User sender, String rawText) {

    }
}

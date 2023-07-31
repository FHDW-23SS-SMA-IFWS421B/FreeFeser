package de.fhdw.freefeser.app.chatbot;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;
import de.fhdw.freefeser.api.user.User;
import de.fhdw.freefeser.api.user.UserManager;

import java.util.ArrayList;
import java.util.Collection;

public class AppChatbotManager implements ChatbotManager {

    private final Collection<Chatbot> bots;

    public AppChatbotManager() {
        this.bots = new ArrayList<>();
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

    private Chatbot extractBot(String firstPart) {
        if(!firstPart.startsWith("@")) {
            return null;
        }

        String botName = firstPart.substring(1);

        for (Chatbot bot : bots) {
            if(bot.getName().equalsIgnoreCase(botName)) {
                return bot;
            }
        }

        return null;
    }
}

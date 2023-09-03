package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.bot.Chatbot;
import de.fhdw.freefeser.api.bot.ChatbotManager;

public class Utils {

    public static boolean isNotUserMessage(ChatbotManager chatbotManager, String text) {
        return !isSystemMessage(text) || !isHistoryMessage(text) || !isBotMessage(chatbotManager, text);
    }

    public static boolean isSystemMessage(String text) {
        return text.startsWith("[system]");
    }

    public static boolean isHistoryMessage(String text) {
        return text.startsWith("[history]");
    }

    public static boolean isBotMessage(ChatbotManager chatbotManager, String text) {
        for (Chatbot bot : chatbotManager.getBots()) {
            if(text.startsWith("["+bot.getName()+"]")) return true;
        }
        return false;
    }
}

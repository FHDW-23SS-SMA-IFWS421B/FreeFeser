package de.fhdw.freefeser.chatbot.models;

import de.fhdw.freefeser.chatbot.interfaces.Bot;
import de.fhdw.freefeser.chatbot.interfaces.Message;
import de.fhdw.freefeser.chatbot.interfaces.User;

import java.util.ArrayList;
import java.util.List;

public class ChatSession {
    private final String sessionId;
    private final User user;
    private final Bot bot;
    private final List<Message> messages;

    public ChatSession(String sessionId, User user, Bot bot) {
        this.sessionId = sessionId;
        this.user = user;
        this.bot = bot;
        this.messages = new ArrayList<>();
    }

    public String getSessionId() {
        return sessionId;
    }

    public User getUser() {
        return user;
    }

    public Bot getBot() {
        return bot;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}

package de.fhdw.freefeser.api.database;

import java.time.LocalDateTime;

public interface ChatMessage {

    long getId();

    void setId(long id);

    String getText();

    void setText(String text);

    LocalDateTime getTimestamp();

    void setTimestamp(LocalDateTime timemestamp);

    User getUser();

    void setUser(User user);

    Chatbot getChatbot();

    void setChatbot(Chatbot chatbot);
}

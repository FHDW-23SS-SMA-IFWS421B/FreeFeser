package de.fhdw.freefeser.api.database;

import java.time.LocalDateTime;

public interface ChatMessage<U extends User, C extends Chatbot> {

    long getId();

    void setId(long id);

    String getText();

    void setText(String text);

    LocalDateTime getTimestamp();

    void setTimestamp(LocalDateTime timemestamp);

    U getUser();

    void setUser(U user);

    C getChatbot();

    void setChatbot(C chatbot);
}

package de.fhdw.freefeser.api.database;

public interface Chatbot {

    long getId();

    void setId(long Id);

    String getBotname();

    void setBotname(String botname);

    boolean getStatus();

    void setStatus(boolean status);
}

package de.fhdw.freefeser.app.databases;

import de.fhdw.freefeser.api.database.Chatbot;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppChatbot implements Chatbot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String botname;

    private boolean active;

    public AppChatbot() {

    }

    public AppChatbot(String botname, boolean active) {
        this.botname = botname;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBotname() {
        return botname;
    }

    public void setBotname(String botname) {
        this.botname = botname;
    }

    public boolean getStatus() {
        return active;
    }

    public void setStatus(boolean active) {
        this.active = active;
    }
}

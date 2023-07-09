package de.fhdw.freefeser.app.prototype.databases;

import de.fhdw.freefeser.api.database.Chatbot;

import javax.persistence.*;

@Entity
@Table(name = "chatbots")
public class AppChatbot implements Chatbot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "botname", nullable = false, unique = true)
    private String botname;

    @Column(name = "active", nullable = false)
    private boolean status;

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
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

package de.fhdw.freefeser.app.databases.entities;

import de.fhdw.freefeser.api.database.ChatbotEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AppChatbotEntity implements ChatbotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String botname;

    private boolean active;

    public AppChatbotEntity() {

    }

    public AppChatbotEntity(String botname, boolean active) {
        this.botname = botname;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

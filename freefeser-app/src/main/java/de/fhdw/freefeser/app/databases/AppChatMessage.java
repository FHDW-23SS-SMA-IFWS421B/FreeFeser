package de.fhdw.freefeser.app.databases;

import de.fhdw.freefeser.api.database.ChatMessage;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AppChatMessage implements ChatMessage<AppUser, AppChatbot> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppChatbot chatbot;

    public AppChatMessage() {

    }

    public AppChatMessage(String text, LocalDateTime timestamp, AppUser user, AppChatbot chatbot) {
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
        this.chatbot = chatbot;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public AppChatbot getChatbot() {
        return chatbot;
    }

    public void setChatbot(AppChatbot chatbot) {
        this.chatbot = chatbot;
    }
}

package de.fhdw.freefeser.app.databases.entities;

import de.fhdw.freefeser.api.database.ChatMessageEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AppChatMessageEntity implements ChatMessageEntity<AppUserEntity, AppChatbotEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppChatbotEntity chatbot;

    public AppChatMessageEntity() {

    }

    public AppChatMessageEntity(String text, LocalDateTime timestamp, AppUserEntity user, AppChatbotEntity chatbot) {
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

    public AppUserEntity getUser() {
        return user;
    }

    public void setUser(AppUserEntity user) {
        this.user = user;
    }

    public AppChatbotEntity getChatbot() {
        return chatbot;
    }

    public void setChatbot(AppChatbotEntity chatbot) {
        this.chatbot = chatbot;
    }
}

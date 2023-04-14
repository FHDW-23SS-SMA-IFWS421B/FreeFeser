package de.fhdw.freefeser.databases;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {
    @Id
    private int id;
    @ManyToOne
    private User user;
    private String message;
    private LocalDateTime timestamp;
    // constructor, getters, setters
}

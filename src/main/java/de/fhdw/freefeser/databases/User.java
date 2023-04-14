package de.fhdw.freefeser.databases;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {
    @Id
    private int id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<ChatMessage> chatMessages;
    // constructor, getters, setters
}

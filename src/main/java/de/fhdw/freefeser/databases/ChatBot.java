package de.fhdw.freefeser.databases;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ChatBot {
    @Id
    private int id;
    private String name;
    private boolean isActive;
    // constructor, getters, setters
}

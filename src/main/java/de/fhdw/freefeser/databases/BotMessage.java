package de.fhdw.freefeser.databases;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BotMessage {
    @Id
    private int id;
    @ManyToOne
    private ChatBot bot;
    private String message;
    // constructor, getters, setters
}

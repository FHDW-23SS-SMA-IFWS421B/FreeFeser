package de.fhdw.freefeser.databases;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AvailableBot {
    @Id
    private int id;
    @ManyToOne
    private ChatBot bot;
    @ManyToOne
    private User user;
    // constructor, getters, setters
}

package de.fhdw.freefeser.databases;

import javax.persistence.*;

@Entity
@Table(name = "available_bots")
public class AvailableBot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bot_id", nullable = false)
    private Chatbot bot;

    @Column(nullable = false)
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chatbot getBot() {
        return bot;
    }

    public void setBot(Chatbot bot) {
        this.bot = bot;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}


package de.fhdw.freefeser.api.database;

import java.util.UUID;

public interface ChatbotEntity {

    UUID getId();

    void setId(UUID Id);

    String getBotname();

    void setBotname(String botname);

    boolean getStatus();

    void setStatus(boolean active);
}

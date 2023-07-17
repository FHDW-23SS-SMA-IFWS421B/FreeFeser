package de.fhdw.freefeser.api.database;

import java.util.UUID;

public interface User {

    UUID getId();

    void setId(UUID id);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);
}

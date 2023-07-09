package de.fhdw.freefeser.api.database;
public interface User {

    long getId();

    void setId(long id);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);
}

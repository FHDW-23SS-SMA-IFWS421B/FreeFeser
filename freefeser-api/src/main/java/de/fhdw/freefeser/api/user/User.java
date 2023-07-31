package de.fhdw.freefeser.api.user;

import de.fhdw.freefeser.api.database.UserEntity;

public interface User {

    void sendMessage(String message);

    UserEntity getEntity();
}

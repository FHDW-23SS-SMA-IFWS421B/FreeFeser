package de.fhdw.freefeser.app.databases.entities;

import de.fhdw.freefeser.api.database.UserEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AppUserEntity implements UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    private String password;

    public AppUserEntity() {

    }

    public AppUserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

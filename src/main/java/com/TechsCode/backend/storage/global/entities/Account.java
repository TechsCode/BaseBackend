package com.techscode.backend.storage.global.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String email;
    private String username;

    @Column(length = 60)
    private String password;

    private String sessionToken;

    private boolean verified;

    @OneToOne
    @PrimaryKeyJoinColumn
    private DiscordConnection discordConnection;

    @OneToOne
    @PrimaryKeyJoinColumn
    private SpigotConnection spigotConnection;

    public Account() {
        this.verified = false;

        regenerateSessionToken();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public DiscordConnection getDiscordConnection() {
        return discordConnection;
    }

    public void setDiscordConnection(DiscordConnection discordConnection) {
        this.discordConnection = discordConnection;
    }

    public SpigotConnection getSpigotConnection() {
        return spigotConnection;
    }

    public void setSpigotConnection(SpigotConnection spigotConnection) {
        this.spigotConnection = spigotConnection;
    }

    public void regenerateSessionToken(){
        sessionToken = UUID.randomUUID().toString().replace("-", "");
    }
}

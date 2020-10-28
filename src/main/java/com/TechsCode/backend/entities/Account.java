package com.techscode.backend.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String email;
    private String username;

    @Column(length = 60)
    private String password;

    private String sessionToken;

    private boolean verified;

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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void regenerateSessionToken(){
        sessionToken = UUID.randomUUID().toString().replace("-", "");
    }
}

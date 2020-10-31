package com.techscode.backend.storage.global.entities;

import javax.persistence.*;

@Entity
@Table(name = "DiscordConnections")
public class DiscordConnection {

    @Id
    @Column(name = "account_id")
    private Integer id;

    private String discordName;
    private String discordId;

    @OneToOne
    @PrimaryKeyJoinColumn
    @MapsId
    private Account account;

    public DiscordConnection() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

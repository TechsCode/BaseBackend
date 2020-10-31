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

    public DiscordConnection() {}
}

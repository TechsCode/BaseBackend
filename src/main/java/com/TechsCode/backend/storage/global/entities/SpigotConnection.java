package com.techscode.backend.storage.global.entities;

import javax.persistence.*;

@Entity
@Table(name = "SpigotConnections")
public class SpigotConnection {

    @Id
    @Column(name = "account_id")
    private Integer id;

    private String spigotName;

    @OneToOne
    @PrimaryKeyJoinColumn
    @MapsId
    private Account account;

    public SpigotConnection() {}


}

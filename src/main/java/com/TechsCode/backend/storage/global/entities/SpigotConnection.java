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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpigotName() {
        return spigotName;
    }

    public void setSpigotName(String spigotName) {
        this.spigotName = spigotName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

package com.techscode.backend.repositories.database;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

public class DatabaseCredentials {

    private final String host, port, username, password;

    public DatabaseCredentials(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public DataSource createDataSource(String database){
        return DataSourceBuilder.create()
                .username(username)
                .password(password)
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://"+host+":"+port+"/"+database)
                .build();
    }
}

package org.example;

import java.sql.Connection;

public class Connect {
    private String url;
    private Connection connection;
    private String username;
    private String password;

    public Connect(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
}

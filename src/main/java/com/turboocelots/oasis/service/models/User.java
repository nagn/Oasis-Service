package com.turboocelots.oasis.service.models;

import java.sql.Timestamp;

/**
 * Created by mlin on 2/25/17.
 */
public class User {
    private long id;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String title;
    private String address;
    private String phoneNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean banned;

    public User (long id, String userName,
                 String password, String name,
                 String email, String title,
                 String address, String phoneNumber,
                 Timestamp createdAt, Timestamp updatedAt, boolean banned) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
        this.title = title;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.banned = banned;
    }
    // Getters and setters
}

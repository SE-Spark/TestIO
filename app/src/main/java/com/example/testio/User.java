package com.example.testio;

public class User {

    private int id;
    private String username, code, type;

    public User(int id, String username, String code, String type) {
        this.id = id;
        this.username = username;
        this.code = code;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
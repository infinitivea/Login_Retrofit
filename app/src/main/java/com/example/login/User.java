package com.example.login;

import java.util.List;

public class User {
    private Integer userID;
    private String username;
    private List<User> results;

    public User(Integer userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}

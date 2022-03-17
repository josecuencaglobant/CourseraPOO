package com.example.sharingapp.finalexam;

import java.util.UUID;

public class Contact {

    private String username;
    private String email;
    private String id;

    public Contact(){
        setId();
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public void updateId(String id){
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

    @Override
    public String toString() {
        return getUsername();
    }
}

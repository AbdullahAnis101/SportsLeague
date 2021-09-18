package com.example.Sports_League.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {
    private final String Username;
    private final UUID User_id;
    private final String Password;
    private final int score;


    public User(@JsonProperty("name") String UN,@JsonProperty("id") UUID id,@JsonProperty("password") String pw){
        Username = UN;
        User_id = id;
        Password = pw;
        score = 0;
    }

    public String getUsername() {
        return Username;
    }

    public UUID getUser_id() {
        return User_id;
    }

    public String getPassword() {
        return Password;
    }

    public int getScore(){
        return score;
    }
}
package com.example.loginapp;

public class User {

    public String fullName,email,uid;

    public User() {

    }


    public String getUserName() {
        return fullName;
    }
    public String getUserEmail(){
        return email;
    }

    public User(String fullName, String email, String uid) {
        this.fullName = fullName;
        this.email = email;
        this.uid = uid;
    }

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}


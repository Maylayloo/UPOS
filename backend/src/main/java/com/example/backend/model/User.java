package com.example.backend.model;

import jakarta.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)  //apparently it helps with a database, diffrent tables for diffrent roles
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;
    private String name;
    private String surname;
    //private String rola; //not sure if we meant one role or multiple

    public User() {}

    public User(String email, String password, String name, String surname, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        //this.rola = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

//    public String getRola() {
//        return rola;
//    }
//
//    public void setRola(String rola) {
//        this.rola = rola;
//    }
}

package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userId;

    private String email;
    private String password;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Role role; //not sure if we meant one role or multiple
    private String number;
    private String address;
    private String bankNumber;

    public MyUser(String email, String password, String name, String surname, Role role, String number, String address, String bankNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.number = number;
        this.address = address;
        this.bankNumber = bankNumber;
    }

    public MyUser() {}


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



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

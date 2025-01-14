package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Professor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profId;
    private Long userId;
    private String title;

    public Professor() {}

    public Professor(String email, String password, String name, String surname, String rola, Long profId, String title) {

        this.profId = profId;
        this.title = title;
    }

    public Long getProfId() {
        return profId;
    }

    public void setProfId(Long profId) {
        this.profId = profId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

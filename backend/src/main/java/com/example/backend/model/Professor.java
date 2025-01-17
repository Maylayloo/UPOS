package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Professor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profId;
    private Long userId;
    private String title;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private MyUser user;

    public Professor() {}

    public Professor(String email, String password, String name, String surname, String rola, Long profId, String title) {

        this.profId = profId;
        this.title = title;
    }

    public Long getProfessorId() {
        return profId;
    }

    public void setProfessorId(Long profId) {
        this.profId = profId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public MyUser getUser() { return user; }
//
//    public void setUser(MyUser user) { this.user = user; }


    public Long getProfId() {
        return profId;
    }

    public void setProfId(Long profId) {
        this.profId = profId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

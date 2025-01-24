package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;
    private Long courseId;
    private Long professorId;
    private String date;
    private String place;
    private String attempt;

    public Exam() {}

    public Exam(Long examId, Long courseId,Long professorId, String date, String place, String attempt) {
        this.examId = examId;
        this.courseId = courseId;
        this.professorId = professorId;
        this.date = date;
        this.place = place;
        this.attempt = attempt;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAttempt() { return attempt; }

    public void setAttempt(String attempt) { this.attempt = attempt; }

    public Long getProfessorId() { return professorId; }

    public void setProfessorId(Long professorId) { this.professorId = professorId; }
}

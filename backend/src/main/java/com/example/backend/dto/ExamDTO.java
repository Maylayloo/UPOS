package com.example.backend.dto;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public class ExamDTO {
    private Long courseId;
    private Long professorId;
    private String date;
    private String place;
    private String attempt;
    private LocalTime startOfExam;

    public ExamDTO(){}

    public LocalTime getStartOfExam() {
        return startOfExam;
    }

    public void setStartOfExam(LocalTime startOfExam) {
        this.startOfExam = startOfExam;
    }

    public Long getCourseId() {return courseId;}

    public void setCourseId(Long courseId) {this.courseId = courseId;}

    public Long getProfessorId() {return professorId;}

    public void setProfessorId(Long professorId) {this.professorId = professorId;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getPlace() {return place;}

    public void setPlace(String place) {this.place = place;}

    public String getAttempt() {return attempt;}

    public void setAttempt(String attempt) {this.attempt = attempt;}
}



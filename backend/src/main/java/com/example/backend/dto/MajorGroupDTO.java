package com.example.backend.dto;

import com.example.backend.model.DayOfTheWeek;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalTime;

public class MajorGroupDTO {
    private Long courseId;
    private String type;
    private int numberOfGroup;
    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfTheWeek;
    private LocalTime startOfLesson;
    private LocalTime endOfLesson;
    private String place;
    private int maxStudentAmount;
    private Long professorId;


    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public LocalTime getStartOfLesson() {
        return startOfLesson;
    }

    public void setStartOfLesson(LocalTime startOfLesson) {
        this.startOfLesson = startOfLesson;
    }

    public LocalTime getEndOfLesson() {
        return endOfLesson;
    }

    public void setEndOfLesson(LocalTime endOfLesson) {
        this.endOfLesson = endOfLesson;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMaxStudentAmount() {
        return maxStudentAmount;
    }

    public void setMaxStudentAmount(int maxStudentAmount) {
        this.maxStudentAmount = maxStudentAmount;
    }
}

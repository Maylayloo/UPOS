package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MajorGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private Long courseId;
    private String type;
    private int numberOfGroup;
    private String date;
    private String place;
    private int maxStudentAmount;

    public MajorGroup() {}

    public MajorGroup(Long courseId, String type, int numberOfGroup, String date, String place, int maxStudentAmount) {
        this.courseId = courseId;
        this.type = type;
        this.numberOfGroup = numberOfGroup;
        this.date = date;
        this.place = place;
        this.maxStudentAmount = maxStudentAmount;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public int getMaxStudentAmount() {
        return maxStudentAmount;
    }

    public void setMaxStudentAmount(int maxStudentAmount) {
        this.maxStudentAmount = maxStudentAmount;
    }
}

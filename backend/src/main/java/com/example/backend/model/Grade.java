package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;
    private Long studentId;
    private Long groupId;
    private boolean isPartial;
    private String value;

    public Grade() {}

    public Grade(Long studentId, Long groupId, boolean isPartial, String value) {
        this.studentId = studentId;
        this.groupId = groupId;
        this.isPartial = isPartial;
        this.value = value;
    }

    public Grade(Long gradeId, Long studentId, Long groupId, boolean isPartial, String value) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.groupId = groupId;
        this.isPartial = isPartial;
        this.value = value;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public boolean isPartial() {
        return isPartial;
    }

    public void setPartial(boolean partial) {
        isPartial = partial;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FinalGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long finalGradeId;
    private Long studentId;
    private Long courseId;
    private String value;

    public FinalGrade() {}

    public FinalGrade(Long studentId, Long courseId, String value) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.value = value;
    }

    public Long getFinalGradeId() {
        return finalGradeId;
    }

    public void setFinalGradeId(Long finalGradeId) {
        this.finalGradeId = finalGradeId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

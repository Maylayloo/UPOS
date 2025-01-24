package com.example.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private int ects;
    private Long professorId;
    private String semester;
    private String major;
    @ElementCollection
    private List<Long> studentsIds;

    //We probably need a list of exams if we want to contain them inside courses
    @Transient //I suppose we don't want another mapped column in a database with exams
    private List<Exam> exams = new ArrayList<>();

    public Course() {}

    public Course(Long courseId, String name, int ects, Long professorId, String semester, String major, List<Long> studentsIds) {
        this.courseId = courseId;
        this.name = name;
        this.ects = ects;
        this.professorId = professorId;
        this.semester = semester;
        this.major = major;
        this.exams = new ArrayList<>();
        this.studentsIds = studentsIds;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public Long getProfessorId() { return professorId; }

    public void setProfessorId(Long professorId) { this.professorId = professorId; }

    public String getSemester() { return semester; }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void addExam(Exam exam) {
        this.exams.add(exam);
    }

    public List<Long> getStudentsIds() { return studentsIds; }

    public void setStudentsIds(List<Long> studentsIds) { this.studentsIds = studentsIds; }
}

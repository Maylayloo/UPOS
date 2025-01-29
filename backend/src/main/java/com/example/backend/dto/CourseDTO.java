package com.example.backend.dto;


public class CourseDTO {
    private String name;
    private int ects;
    private Long professorId;
    private String semester;
    private String major;


    public void setName(String name) {
        this.name = name;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public String getSemester() {
        return semester;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public int getEcts() {
        return ects;
    }

    public String getName() {
        return name;
    }

}

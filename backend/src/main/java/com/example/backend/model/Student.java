package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Student  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private Long userId;
    private String indexNumber;
    private String faculty;
    private String major;
    private String dateOfBirth;
    private String phoneNumber;
    private int semester;

    @OneToOne
    @JoinColumn(name = "user_id")
    private MyUser user;

    public Student() {}

    public Student(String email, String password, String name, String surname, String rola, String indexNumber,
                   String faculty, String major, String dateOfBirth, String phoneNumber, int semester) {

        this.indexNumber = indexNumber;
        this.faculty = faculty;
        this.major = major;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.semester = semester;
    }


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public MyUser getUser() { return user; }

    public void setUser(MyUser user) { this.user = user; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}

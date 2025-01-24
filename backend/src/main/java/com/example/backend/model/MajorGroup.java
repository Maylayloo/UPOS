package com.example.backend.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
public class MajorGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private Long courseId;
    private String type;
    private int numberOfGroup;
    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfTheWeek;
    private LocalTime startOfLesson;
    private LocalTime endOfLesson;
    private String place;
    private int maxStudentAmount;
    @ElementCollection
    private List<Long> studentsIds;

    public MajorGroup() {}

    public MajorGroup( String type, int numberOfGroup, DayOfTheWeek dayOfTheWeek, LocalTime
            startOfLesson, LocalTime endOfLesson, String place, int maxStudentAmount, List<Long> studentsIds) {
        this.type = type;
        this.numberOfGroup = numberOfGroup;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startOfLesson = startOfLesson;
        this.endOfLesson = endOfLesson;
        this.place = place;
        this.maxStudentAmount = maxStudentAmount;
        this.studentsIds = studentsIds;
    }
    public MajorGroup(Long groupId, String type, int numberOfGroup, DayOfTheWeek dayOfTheWeek, LocalTime
            startOfLesson, LocalTime endOfLesson, String place, int maxStudentAmount, List<Long> studentsIds) {
        this.groupId = groupId;
        this.type = type;
        this.numberOfGroup = numberOfGroup;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startOfLesson = startOfLesson;
        this.endOfLesson = endOfLesson;
        this.place = place;
        this.maxStudentAmount = maxStudentAmount;
        this.studentsIds = studentsIds;
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

    public List<Long> getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(List<Long> studentsIds) {
        this.studentsIds = studentsIds;
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
}

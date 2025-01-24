package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

public class StudentCourseDTO {
    int semester;
    String major;

    public int getSemester() {
        return semester;
    }

    public String getMajor() {
        return major;
    }
}

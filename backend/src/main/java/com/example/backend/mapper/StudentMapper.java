package com.example.backend.mapper;

import com.example.backend.dto.StudentDTO;
import com.example.backend.model.Student;

public class StudentMapper {

    public static Student toModel(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFaculty(studentDTO.getFaculty());
        student.setMajor(studentDTO.getMajor());
        student.setIndexNumber(studentDTO.getIndexNumber());
        student.setSemester(studentDTO.getSemester());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        return student;
    }
}

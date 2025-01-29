package com.example.backend.mapper;

import com.example.backend.dto.CourseDTO;
import com.example.backend.model.Course;

public class CourseMapper {
    public static Course toModel(CourseDTO courseDTO){
        Course course = new Course();

        course.setName(courseDTO.getName());
        course.setEcts(courseDTO.getEcts());
        course.setMajor(courseDTO.getMajor());
        course.setSemester(courseDTO.getSemester());
        course.setProfessorId(courseDTO.getProfessorId());
        return course;}

}

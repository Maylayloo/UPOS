package com.example.backend.service.professorService;

import com.example.backend.model.Course;
import com.example.backend.model.MajorGroup;
import com.example.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseManagementForProfessorService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getCourses(Long professorId) {
        List<Course> courses = courseRepository.findByProfessorId(professorId);
        return courses != null ? courses : new ArrayList<>();
    }
    public List<MajorGroup> getMajorGroupsByLoggedInProfessor() {return null;}

    public List<MajorGroup> getMajorGroupsByLoggedInProfessorAndCourseId(Long id) {return null;}

    public List<Course> getCoursesByLoggedInProfessor() {return null;}
}

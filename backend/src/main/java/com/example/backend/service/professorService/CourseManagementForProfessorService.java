package com.example.backend.service.professorService;

import com.example.backend.exception.InvalidAssociationException;
import com.example.backend.exception.ProfessorAccessException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Course;
import com.example.backend.model.MajorGroup;

import com.example.backend.model.Professor;
import com.example.backend.model.Student;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.MajorGroupRepository;
import com.example.backend.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CourseManagementForProfessorService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MajorGroupRepository majorGroupRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorAuthenticationService professorAuthenticationService;

    public List<Course> getCourses(Long professorId) {
        List<Course> courses = courseRepository.findByProfessorId(professorId);
        return courses != null ? courses : new ArrayList<>();
    }

    public List<MajorGroup> getMajorGroupsByLoggedInProfessor() {
        Professor loggedInProfessor = professorAuthenticationService.getLoggedInProfessor();
        if (loggedInProfessor == null) {
            throw new ProfessorAccessException("No professor is currently logged in");
        }

        List<Course> professorCourses = courseRepository.findByProfessorId(loggedInProfessor.getProfessorId());
        return professorCourses.stream()
                .flatMap(course -> majorGroupRepository.findByCourseId(course.getCourseId()).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<MajorGroup> getMajorGroupsByLoggedInProfessorAndCourseId(Long courseId) {
        Professor loggedInProfessor = professorAuthenticationService.getLoggedInProfessor();
        if (loggedInProfessor == null) {
            throw new ProfessorAccessException("No professor is currently logged in");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course with ID " + courseId + " not found"));

        if (!course.getProfessorId().equals(loggedInProfessor.getProfessorId())) {
            throw new InvalidAssociationException("Logged-in professor does not own this course");
        }

        return majorGroupRepository.findByCourseId(courseId);
    }

    public List<Course> getCoursesByLoggedInProfessor() {
        Professor loggedInProfessor = professorAuthenticationService.getLoggedInProfessor();
        if (loggedInProfessor == null) {
            throw new ProfessorAccessException("No professor is currently logged in");
        }

        return courseRepository.findByProfessorId(loggedInProfessor.getProfessorId());
    }

    public List<MajorGroup> getMajorGroupsByCourseId(Long courseId) {
        return majorGroupRepository.findByCourseId(courseId);
    }
    public List<Course> getCoursesByProfessorLoggedIn(){
        Professor loggedInProfessor = professorAuthenticationService.getLoggedInProfessor();
        return courseRepository.findAll().stream().filter(course->course.getStudentsIds().
                contains(loggedInProfessor.getProfessorId())).collect(Collectors.toList());
    }


}

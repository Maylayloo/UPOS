package com.example.backend.service.studentService;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.exception.StudentAccessException;
import com.example.backend.model.Course;
import com.example.backend.model.MajorGroup;
import com.example.backend.model.Student;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.MajorGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseForStudentService {

    @Autowired
    private StudentAuthenticationService studentAuthenticationService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MajorGroupRepository majorGroupRepository;


    public List<MajorGroup> getMajorGroupsByLoggedInStudent() {
        Student loggedInStudent = studentAuthenticationService.getLoggedInStudent();
        if (loggedInStudent == null) {
            throw new StudentAccessException("No student is currently logged in");
        }

        return majorGroupRepository.findAll().stream()
                .filter(group -> group.getStudentsIds().contains(loggedInStudent.getStudentId()))
                .distinct()
                .collect(Collectors.toList());
    }

    public List<MajorGroup> getMajorGroupsByLoggedInStudentAndCourseId(Long courseId) {
        Student loggedInStudent = studentAuthenticationService.getLoggedInStudent();
        if (loggedInStudent == null) {
            throw new StudentAccessException("No student is currently logged in");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course with ID " + courseId + " not found"));

        return majorGroupRepository.findByCourseId(courseId).stream()
                .filter(group -> group.getStudentsIds().contains(loggedInStudent.getStudentId()))
                .collect(Collectors.toList());
    }
    public List<Course> getCoursesByLoggedInStudent() {
        Student loggedInStudent = studentAuthenticationService.getLoggedInStudent();
        if (loggedInStudent == null) {
            throw new StudentAccessException("No student is currently logged in");
        }

        // Get all students groups
        List<Long> courseIds = majorGroupRepository.findAll().stream()
                .filter(group -> group.getStudentsIds().contains(loggedInStudent.getStudentId()))
                .map(MajorGroup::getCourseId) // get courseId for each group
                .distinct()
                .collect(Collectors.toList());

        // get courses by courseId from groups
        return courseRepository.findAllById(courseIds);
    }
    public List<Course> getCoursesByStudentLoggedIn(){
        Student loggedInStudent = studentAuthenticationService.getLoggedInStudent();
        return courseRepository.findAll().stream().filter(course->course.getStudentsIds().
                contains(loggedInStudent.getStudentId())).collect(Collectors.toList());
    }
    public void addToWF(){}
}

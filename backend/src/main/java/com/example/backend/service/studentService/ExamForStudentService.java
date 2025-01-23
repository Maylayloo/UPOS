package com.example.backend.service.studentService;

import com.example.backend.model.Course;
import com.example.backend.model.Exam;
import com.example.backend.model.MajorGroup;
import com.example.backend.model.Student;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.ExamRepository;
import com.example.backend.repository.MajorGroupRepository;
import com.example.backend.service.professorService.ExamManagementForProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamForStudentService {
    @Autowired
    private StudentAuthenticationService studentAuthenticationService;


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAllExamsByLoggedInStudent() {
        Student loggedInStudent = studentAuthenticationService.getLoggedInStudent();
        if (loggedInStudent == null) {
            throw new RuntimeException("No student is currently logged in");
        }

        List<Course> studentCourses = courseRepository.findAll().stream()
                .filter(course -> course.getStudentsIds().contains(loggedInStudent.getStudentId()))
                .collect(Collectors.toList());

        return studentCourses.stream()
                .flatMap(course -> examRepository.findByCourseId(course.getCourseId()).stream())
                .distinct()
                .collect(Collectors.toList());
    }

}

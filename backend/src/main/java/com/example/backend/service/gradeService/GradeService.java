package com.example.backend.service.gradeService;

import com.example.backend.model.Grade;
import com.example.backend.model.MyUser;
import com.example.backend.model.Role;
import com.example.backend.model.Student;
import com.example.backend.repository.GradeRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;

    public List<Grade> showGradesByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public List<Grade> showGradesByGradeId(Long gradeId) {
        List<Grade> grades = gradeRepository.findByGradeId(gradeId);
        if (grades.isEmpty()) {
            throw new RuntimeException("No grades found for gradeId: " + gradeId);
        }
        return grades;
    }

    public List<Grade> showAllGrades() {return gradeRepository.findAll();}

    public List<Grade> showAllGradesByLoggedInStudent() {
        MyUser loggedInUser = userWebAuthenticationService.getLoggedInUser();

        if (loggedInUser.getRole() != Role.STUDENT) {
            throw new RuntimeException("Logged-in user is not a student");
        }

        Student student = studentRepository.findByUser_UserId(loggedInUser.getUserId())
                .orElseThrow(() -> new RuntimeException("Could not find student with userId: " + loggedInUser.getUserId()));

        return gradeRepository.findByStudentId(student.getStudentId());
    }
}


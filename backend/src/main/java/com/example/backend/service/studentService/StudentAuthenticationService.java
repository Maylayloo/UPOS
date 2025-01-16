package com.example.backend.service.studentService;

import com.example.backend.model.MyUser;
import com.example.backend.model.Student;
import com.example.backend.model.Role;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAuthenticationService {

    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;

    @Autowired
    private StudentRepository studentRepository;

    public Student getLoggedInStudent() {
        MyUser loggedInUser = userWebAuthenticationService.getLoggedInUser();

        if (loggedInUser.getRole() != Role.STUDENT) {
            throw new RuntimeException("Logged-in user is not a student");
        }

        return studentRepository.findByUser_UserId(loggedInUser.getUserId())
                .orElseThrow(() -> new RuntimeException("Could not find student with userId: " + loggedInUser.getUserId()));
    }
}

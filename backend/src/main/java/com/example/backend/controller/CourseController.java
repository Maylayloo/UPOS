package com.example.backend.controller;

import com.example.backend.service.professorService.CourseManagementForProfessorService;
import com.example.backend.service.studentService.CourseForStudentService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import com.example.backend.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseForStudentService groupForStudentService;
    @Autowired
    private CourseManagementForProfessorService courseManagementForProfessorService;
    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;
    @GetMapping("/loggedIn")
    public ResponseEntity<?> loggedIn() {
        if(userWebAuthenticationService.isLoggedInStudent()){
            return new ResponseEntity<>(groupForStudentService.getCoursesByStudentLoggedIn(),HttpStatus.OK);
        }
        else if(userWebAuthenticationService.isLoggedInProfessor()){
            return new ResponseEntity<>(courseManagementForProfessorService.getCoursesByProfessorLoggedIn(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }

    }
}

package com.example.backend.controller;

import com.example.backend.model.MajorGroup;
import com.example.backend.service.professorService.CourseManagementForProfessorService;
import com.example.backend.service.studentService.CourseForStudentService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import com.example.backend.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private CourseForStudentService groupForStudentService;
    @Autowired
    private CourseManagementForProfessorService courseManagementForProfessorService;
    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;
    @GetMapping("/loggedIn")
    public ResponseEntity<List<MajorGroup>> getAllGroupsByLoggerInUser() {
        if(userWebAuthenticationService.isLoggedInStudent()) {
            return new  ResponseEntity<>(groupForStudentService.getMajorGroupsByLoggedInStudent(),HttpStatus.OK);
        }
        else if(userWebAuthenticationService.isLoggedInProfessor()) {
            return new ResponseEntity<>(courseManagementForProfessorService.getMajorGroupsByLoggedInProfessor(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/loggedIn/{id}")
    public ResponseEntity<List<MajorGroup>> getGroupsByCourseId(@PathVariable Long id) {
        if(userWebAuthenticationService.isLoggedInStudent()) {
            return new  ResponseEntity<>(groupForStudentService.getMajorGroupsByLoggedInStudentAndCourseId(id),HttpStatus.OK);
        }
        else if(userWebAuthenticationService.isLoggedInProfessor()) {
            return new ResponseEntity<>(courseManagementForProfessorService.getMajorGroupsByLoggedInProfessorAndCourseId(id),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

}

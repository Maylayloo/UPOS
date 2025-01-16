package com.example.backend.controller;

import com.example.backend.service.professorService.CourseManagementForProfessorService;
import com.example.backend.service.studentService.CourseForStudentService;
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

    @GetMapping("/loggedIn")
    public ResponseEntity<?> loggedIn() {
        if(UserUtils.isUserRoleStudent()) {
            return new ResponseEntity<>(groupForStudentService.getCoursesBySLoggedInStudent(),HttpStatus.OK);
        }
        else if(UserUtils.isUserRoleProfessor()){
            return new ResponseEntity<>(courseManagementForProfessorService.getCoursesByLoggedInProfessor(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }
}

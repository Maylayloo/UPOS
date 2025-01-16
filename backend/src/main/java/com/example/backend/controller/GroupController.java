package com.example.backend.controller;

import com.example.backend.model.MajorGroup;
import com.example.backend.service.professorService.CourseManagementForProfessorService;
import com.example.backend.service.studentService.CourseForStudentService;
import com.example.backend.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/loggedIn")
    public ResponseEntity<List<MajorGroup>> getAllGroupsByLoggerInUser() {
        if(UserUtils.isUserRoleStudent()) {
            return new  ResponseEntity<>(groupForStudentService.getGroupsByLoggedInStudent(),HttpStatus.OK);
        }
        else if(UserUtils.isUserRoleAdmin()){
            return new ResponseEntity<>(courseManagementForProfessorService.getMajorGroupsByLoggedInProfessor(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

}

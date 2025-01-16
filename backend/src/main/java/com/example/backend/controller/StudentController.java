package com.example.backend.controller;

import com.example.backend.model.Grade;
import com.example.backend.service.gradeService.GradeService;
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
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/loggedIn/grades")
    public ResponseEntity<List<Grade>> getGradesForLoggedInUser() {
        if(UserUtils.isUserRoleStudent()){
            return new ResponseEntity<>(gradeService.showAllGradesByLoggedInStudent(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<List<Grade>> getGradesByStudentId(@PathVariable Long id) {
        return new ResponseEntity<>(gradeService.showGradesByStudentId(id),HttpStatus.OK);

    }

    




}

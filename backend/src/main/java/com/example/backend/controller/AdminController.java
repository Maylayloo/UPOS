package com.example.backend.controller;

import com.example.backend.dto.*;
import com.example.backend.model.Student;
import com.example.backend.service.adminService.CourseManagementForAdminService;
import com.example.backend.service.adminService.UserManagementForAdminService;
import com.example.backend.service.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserManagementForAdminService userManagementForAdminService;
    private CourseManagementForAdminService courseManagementForAdminService;
    AdminController(UserManagementForAdminService userManagementForAdminService) {
       this.userManagementForAdminService=userManagementForAdminService;
    }

    @PostMapping("/students")
    public ResponseEntity<?> registerStudent(@RequestBody StudentDTO studentDTO, @RequestBody MyUserDTO userDTO) {
        userManagementForAdminService.registerStudent(studentDTO,userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/professors")
    public ResponseEntity<?> registerProfessor(@RequestBody ProfessorDTO professorDTO,@RequestBody MyUserDTO userDTO) {
        userManagementForAdminService.registerProfessor(professorDTO,userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO) {
           courseManagementForAdminService.createCourse(courseDTO);
           return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/groups")
    public ResponseEntity<?> createCourse(@RequestBody MajorGroupDTO majorGroupDTO) {
        courseManagementForAdminService.createMajorGroup(majorGroupDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        userManagementForAdminService.killStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/professors/{id}")
    public ResponseEntity<?> deleteProfessor(@PathVariable Long id) {
        userManagementForAdminService.killProfessor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
       courseManagementForAdminService.deleteCourse(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        courseManagementForAdminService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

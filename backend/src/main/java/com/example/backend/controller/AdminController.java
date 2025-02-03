package com.example.backend.controller;

import com.example.backend.dto.*;
import com.example.backend.dto.wrapper.ProfessorRegisterWrapperDTO;
import com.example.backend.dto.wrapper.StudentRegisterWrapperDTO;
import com.example.backend.model.Student;
import com.example.backend.service.adminService.CourseManagementForAdminService;
import com.example.backend.service.adminService.UserManagementForAdminService;
import com.example.backend.service.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private UserManagementForAdminService userManagementForAdminService;
    private CourseManagementForAdminService courseManagementForAdminService;
    AdminController(UserManagementForAdminService userManagementForAdminService,CourseManagementForAdminService courseManagementForAdminService) {
       this.userManagementForAdminService=userManagementForAdminService;
       this.courseManagementForAdminService=courseManagementForAdminService;
    }

    @PostMapping("/students")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegisterWrapperDTO studentRegisterWrapperDTO) {
        userManagementForAdminService.registerStudent(studentRegisterWrapperDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/professors")
    public ResponseEntity<?> registerProfessor(@RequestBody ProfessorRegisterWrapperDTO professorRegisterWrapperDTO) {
        userManagementForAdminService.registerProfessor(professorRegisterWrapperDTO.getTitle(),professorRegisterWrapperDTO.getMyUserDTO());
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

   @GetMapping("/majors")
   public ResponseEntity<?> getAllMajors() {
        return new ResponseEntity<>(courseManagementForAdminService.getAllMajors(), HttpStatus.OK);
   }

}

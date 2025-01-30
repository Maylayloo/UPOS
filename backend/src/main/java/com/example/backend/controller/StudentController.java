package com.example.backend.controller;

import com.example.backend.dto.IdRequest;
import com.example.backend.dto.StudentCourseDTO;
import com.example.backend.model.Exam;
import com.example.backend.model.Grade;
import com.example.backend.model.Student;
import com.example.backend.service.gradeService.GradeService;
import com.example.backend.service.studentService.ExamForStudentService;
import com.example.backend.service.studentService.StudentService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import com.example.backend.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamForStudentService examForStudentService;

    @GetMapping("/loggedIn/grades")
    public ResponseEntity<List<Grade>> getGradesForLoggedInUser() {
        if(userWebAuthenticationService.isLoggedInStudent()){
            return new ResponseEntity<>(gradeService.showAllGradesByLoggedInStudent(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudentsByMajorAndSemester(@RequestBody StudentCourseDTO studentCourseDTO) {
        return new ResponseEntity<>(studentService.getStudentsBymajorAndSemester(studentCourseDTO.getSemester(),studentCourseDTO.getMajor()),HttpStatus.OK);
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<List<Grade>> getGradesByStudentId(@PathVariable Long id) {
        return new ResponseEntity<>(gradeService.showGradesByStudentId(id),HttpStatus.OK);
    }

    @GetMapping("/{id}/nameAndSurname")
    public ResponseEntity<Map<String,String>> getNamesAndSurnamesByStudentId(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getNameAndSurnameByStudentId(id),HttpStatus.OK);
    }

    @PostMapping("/namesAndSurnames")
    public ResponseEntity<List<Map<String,String>>> getNamesAndSurnames(@RequestBody IdRequest idRequest) {
        return new ResponseEntity<>(studentService.getNamesAndSurnamesByIds(idRequest.getIds()),HttpStatus.OK);
    }

    @GetMapping("/loggedIn/nameAndSurname")
    public ResponseEntity<Map<String,String>> getNamesAndSurnamesByStudentId() {
        return new ResponseEntity<>(studentService.getNameAndSurnameByLoggedInStudent(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudentByStudentId(id),HttpStatus.OK);
    }

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getAllExams() {
        return new ResponseEntity<>(examForStudentService.getAllExamsByLoggedInStudent(),HttpStatus.OK);
    }

    @GetMapping("/loggedIn/grades/{groupid}")
    public ResponseEntity<List<Grade>> getAllGradesByLoggedInUserAndGroupId(@PathVariable Long groupid) {
        return new ResponseEntity<>(gradeService.getAllGradesByLoggedInStudentAndGroupId(groupid),HttpStatus.OK);
    }

    @GetMapping("/loggedIn/grades/NonPartial/{groupid}")
    public ResponseEntity<Grade> getGradesForNoNPartialGradeByLoggedInStudentAndGroupId(@PathVariable Long groupid) {
        return new ResponseEntity<>(gradeService.getNonPartialGradeByStudentAndGroupId(groupid),HttpStatus.OK);
    }



    




}

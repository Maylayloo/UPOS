package com.example.backend.controller;

import com.example.backend.dto.ExamDTO;
import com.example.backend.dto.GradeRequestDTO;
import com.example.backend.dto.ProfessorDTO;
import com.example.backend.model.Exam;
import com.example.backend.service.professorService.ExamManagementForProfessorService;
import com.example.backend.service.professorService.GradeManagementForProfessorService;
import com.example.backend.service.professorService.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private GradeManagementForProfessorService gradeManagementForProfessorService;
    @Autowired
    private ExamManagementForProfessorService examManagementForProfessorService;


    @GetMapping("/{id}/nameAndSurnameAndTitle")
    public ResponseEntity<ProfessorDTO>  getProfessorDTO(@PathVariable("id") Long id) {
        return new ResponseEntity<>(professorService.getProfessorDTOByProfessorId(id),HttpStatus.OK);
    }
    @GetMapping("/loggedIn/nameAndSurnameAndTitle")
    public ResponseEntity<ProfessorDTO>  getProfessorDTOForLoggedIn() {
        return new ResponseEntity<>(professorService.getProfessorDTOByLoggedIn(),HttpStatus.OK);
    }

    @PostMapping("/loggedIn/grades")
    public ResponseEntity<?> addGradeToStudent( @RequestBody GradeRequestDTO gradeRequestDTO) {
        gradeManagementForProfessorService.addGradeByStudentIdAndGroupId(gradeRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/loggedIn/grades/{id}")
    public ResponseEntity<?> updateGradeToStudent( @PathVariable Long id, @RequestBody GradeRequestDTO gradeRequestDTO) {

        gradeManagementForProfessorService.updateGradeByGradeId(id, gradeRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/loggedIn/grades/{id}")
    public ResponseEntity<?> deleteGradeFromStudent( @PathVariable("id") Long id) {
        gradeManagementForProfessorService.deleteGradeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/exams")
    public ResponseEntity<?> getAllExamsByLoggedInProfessor(){
        return new ResponseEntity<>(examManagementForProfessorService.getExamsByLoggedInProfessor(), HttpStatus.OK);
    }

    @PutMapping("/exams/{courseId}")
    public ResponseEntity<?> setExam( @PathVariable Long courseId,@RequestBody ExamDTO examDTO){
        examManagementForProfessorService.scheduleExam(examDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/exams/{courseId}")
    public ResponseEntity<?> modifyExam( @PathVariable Long courseId,@RequestBody Exam exam){
        examManagementForProfessorService.modifyExamById(exam.getExamId(),exam);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

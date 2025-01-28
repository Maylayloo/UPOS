package com.example.backend.service.gradeService;

import com.example.backend.model.*;
import com.example.backend.repository.FinalGradeRepository;
import com.example.backend.repository.GradeRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.studentService.StudentAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private FinalGradeRepository finalGradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAuthenticationService studentAuthenticationService;

    public List<Grade> showGradesByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public List<FinalGrade> showFinalGradesByStudentId(Long studentId) {
        return finalGradeRepository.findByStudentId(studentId);
    }

    public List<Grade> showGradesByGradeId(Long gradeId) {
        List<Grade> grades = gradeRepository.findByGradeId(gradeId);
        if (grades.isEmpty()) {
            throw new RuntimeException("No grades found for gradeId: " + gradeId);
        }
        return grades;
    }

    public FinalGrade showFinalGradeByGradeId(Long finalGradeId) {
        return finalGradeRepository.findById(finalGradeId)
                .orElseThrow(() -> new RuntimeException("Final Grade with ID " + finalGradeId + " not found"));
    }


    public List<Grade> showAllGrades() {return gradeRepository.findAll();}

    public List<Grade> showAllGradesByLoggedInStudent() {
        Student loggedInStudent = studentAuthenticationService.getLoggedInStudent();

        return gradeRepository.findByStudentId(loggedInStudent.getStudentId());
    }

    public List<Grade> getAllGradesByLoggedInStudentAndGroupId(Long groupId) {
        Student loggedInStudent = studentAuthenticationService.getLoggedInStudent();

        return gradeRepository.findByStudentIdAndGroupId(loggedInStudent.getStudentId(), groupId);
    }

    public FinalGrade getFinalGradeByLoggedInStudentAndCourseId(Long courseId) {
        Student loggedInStudent = studentAuthenticationService.getLoggedInStudent();

        if (loggedInStudent == null) {
            throw new RuntimeException("No logged-in student found");
        }

        return finalGradeRepository.findByStudentIdAndCourseId(loggedInStudent.getStudentId(), courseId)
                .orElseThrow(() -> new RuntimeException("No final grade found for student ID "
                        + loggedInStudent.getStudentId() + " and course ID " + courseId));
    }





}


package com.example.backend.service.professorService;

import com.example.backend.model.FinalGrade;
import com.example.backend.model.Grade;
import com.example.backend.repository.FinalGradeRepository;
import com.example.backend.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeManagementForProfessorService {
    @Autowired
    private FinalGradeRepository finalGradeRepository;

    public void setFinalGradesByStudentId(Long studentId, List<FinalGrade> finalGrades) {
        finalGrades.forEach(grade -> grade.setStudentId(studentId)); //Add studentId to every grade
        finalGradeRepository.saveAll(finalGrades);
    }

    public void deleteFinalGradeById(Long finalGradeId) {
        finalGradeRepository.deleteById(finalGradeId);
    }

    @Autowired
    private GradeRepository gradeRepository;

    public void setGradesByStudentId(Long studentId, List<Grade> grades) {
        grades.forEach(grade -> grade.setStudentId(studentId));
        gradeRepository.saveAll(grades);
    }

    public void deleteGradeById(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }
}

package com.example.backend.service.professorService;

import com.example.backend.dto.GradeRequestDTO;
import com.example.backend.exception.ResourceNotFoundException;
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

    public void addGradeByStudentIdAndGroupId(GradeRequestDTO gradeRequestDTO) {
        Grade grade = new Grade();
        grade.setStudentId(gradeRequestDTO.getStudentId());
        grade.setGroupId(gradeRequestDTO.getGroupId());
        grade.setValue(gradeRequestDTO.getValue());
        grade.setPartial(gradeRequestDTO.isPartial());

        gradeRepository.save(grade);
    }

    public void updateGradeByGradeId(Long id,GradeRequestDTO gradeRequestDTO) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade with ID " + id + " not found"));

        grade.setValue(gradeRequestDTO.getValue());
        grade.setPartial(gradeRequestDTO.isPartial());

        gradeRepository.save(grade);
    }

}

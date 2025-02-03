package com.example.backend.repository;

import com.example.backend.model.FinalGrade;
import com.example.backend.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinalGradeRepository extends JpaRepository<FinalGrade, Long> {
    List<FinalGrade> findByStudentId(Long studentId);
    Optional<FinalGrade> findByStudentIdAndCourseId(Long studentId, Long courseId);
    void deleteByStudentId(Long studentId);
}

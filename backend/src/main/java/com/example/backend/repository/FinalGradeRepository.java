package com.example.backend.repository;

import com.example.backend.model.FinalGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinalGradeRepository extends JpaRepository<FinalGrade, Long> {
    List<FinalGrade> findByStudentId(Long studentId);
}

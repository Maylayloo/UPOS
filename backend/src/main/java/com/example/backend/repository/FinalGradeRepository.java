package com.example.backend.repository;

import com.example.backend.model.FinalGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinalGradeRepository extends JpaRepository<FinalGrade, Integer> {
}

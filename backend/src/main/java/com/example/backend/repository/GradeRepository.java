package com.example.backend.repository;

import com.example.backend.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByGradeId(Long gradeId);
    List<Grade> findByStudentIdAndGroupId(Long studentId, Long groupId);
    List<Grade> findByGroupIdAndStudentId(Long groupId, Long studentId);
}

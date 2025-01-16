package com.example.backend.repository;

import com.example.backend.model.Professor;
import com.example.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByUserId(Long userId);
}

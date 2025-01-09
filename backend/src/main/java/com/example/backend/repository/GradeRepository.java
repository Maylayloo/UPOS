package com.example.backend.repository;

import com.example.backend.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GradeRepository extends JpaRepository<Grade, Long> {

}

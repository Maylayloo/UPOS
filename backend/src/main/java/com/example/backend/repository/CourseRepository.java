package com.example.backend.repository;

import com.example.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Integer> {
}

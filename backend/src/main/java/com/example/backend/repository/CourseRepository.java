package com.example.backend.repository;

import com.example.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByProfessorId(Long professorId);

    @Query("SELECT c FROM Course c JOIN c.studentIds s WHERE s = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);

}

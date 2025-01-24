package com.example.backend.repository;

import com.example.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUserId(Long userId);
    @Query("SELECT new map(u.name as name, u.surname as surname) " +
            "FROM Student s JOIN MyUser u ON s.userId = u.userId " +
            "WHERE s.studentId = :id")
    public Map<String, String> getNameAndSurnameByStudentId(@Param("id") Long id);

    @Query("SELECT s FROM Student s WHERE s.semester = :semester AND s.major = :major")
    List<Student> findStudentsBySemesterAndMajor(@Param("semester") int semester, @Param("major") String major);
}

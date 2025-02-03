package com.example.backend.repository;

import com.example.backend.model.MajorGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorGroupRepository extends JpaRepository<MajorGroup, Long> {
    List<MajorGroup> findByCourseId(Long courseId);
    void deleteByCourseId(Long courseId);

    @Query("SELECT m FROM MajorGroup m JOIN m.studentsIds s WHERE s = :studentId")
    List<MajorGroup> findMajorGroupsByStudentId(@Param("studentId") Long studentId);

}

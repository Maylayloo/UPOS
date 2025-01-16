package com.example.backend.repository;

import com.example.backend.model.MajorGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MajorGroupRepository extends JpaRepository<MajorGroup, Long> {
    List<MajorGroup> findByCourseId(Long courseId);
}

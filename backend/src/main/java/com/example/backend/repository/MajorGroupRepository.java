package com.example.backend.repository;

import com.example.backend.model.MajorGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MajorGroupRepository extends JpaRepository<MajorGroup, Long> {
}

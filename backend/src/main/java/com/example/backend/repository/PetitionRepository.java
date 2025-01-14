package com.example.backend.repository;

import com.example.backend.model.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PetitionRepository extends JpaRepository<Petition, Integer> {
}

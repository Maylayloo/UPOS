package com.example.backend.service.professorService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorCleanupService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void removeProfessorReferences(Long professorId) {
        //get all tabels with 'professor_id' in them
        List<String> tables = entityManager.createNativeQuery(
                        "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'professor_id'")
                .getResultList();

        // for each table set their professor_id = null
        for (String table : tables) {
            String query = "UPDATE " + table + " SET professor_id = NULL WHERE professor_id = :id";
            entityManager.createNativeQuery(query)
                    .setParameter("id", professorId)
                    .executeUpdate();
        }
    }
}

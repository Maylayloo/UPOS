package com.example.backend.service.studentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCleanupService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void removeStudentReferences(Long studentId) {
        List<String> tables = entityManager.createNativeQuery(
                        "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'student_id'")
                .getResultList();

        for (String table : tables) {
            if (!table.equalsIgnoreCase("student")) {
                String query = "UPDATE " + table + " SET student_id = NULL WHERE student_id = :id";
                entityManager.createNativeQuery(query)
                        .setParameter("id", studentId)
                        .executeUpdate();
            }
        }
    }
}

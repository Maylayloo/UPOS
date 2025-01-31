package com.example.backend.service.professorService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProfessorCleanupTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private ProfessorCleanupService professorCleanupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveProfessorReferences() {
        // Mock the list of tables
        List<String> tables = List.of("course", "exam", "petition");
        when(entityManager.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'professor_id'"))
                .thenReturn(query);
        when(query.getResultList()).thenReturn(tables);

        // Mock the execution of update queries
        when(entityManager.createNativeQuery(any(String.class))).thenReturn(query);
        when(query.setParameter(eq("id"), any(Long.class))).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        Long professorId = 1L;
        professorCleanupService.removeProfessorReferences(professorId);

        // Verify that the initial query was executed once
        verify(entityManager, times(1)).createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'professor_id'");

        // Verify that the update queries were executed for each table
        for (String table : tables) {
            verify(entityManager, times(1)).createNativeQuery("UPDATE " + table + " SET professor_id = NULL WHERE professor_id = :id");
        }

        // Verify that setParameter and executeUpdate were called for each table
        verify(query, times(tables.size())).setParameter(eq("id"), eq(professorId));
        verify(query, times(tables.size())).executeUpdate();
    }
}

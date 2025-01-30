package ProffesorServiceTest;

import com.example.backend.service.professorService.ProfessorCleanupService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProfessorCleanupServiceTest {

    @InjectMocks
    private ProfessorCleanupService professorCleanupService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query mockSchemaQuery;

    @Mock
    private Query mockUpdateQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 🔹 Sprawiamy, że każda próba stworzenia natywnego zapytania SELECT zwraca `mockSchemaQuery`
        when(entityManager.createNativeQuery(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'professor_id'"))
                .thenReturn(mockSchemaQuery);

        // 🔹 Sprawiamy, że każda próba stworzenia natywnego zapytania UPDATE zwraca `mockUpdateQuery`
        when(entityManager.createNativeQuery(anyString())).thenReturn(mockUpdateQuery);
    }

    @Test
    void testRemoveProfessorReferences_Success() {
        // ✅ Symulujemy tabele zawierające professor_id
        Long professorId = 1L;
        List<String> mockTables = List.of("courses", "exams");

        // ✅ Upewniamy się, że `mockSchemaQuery.getResultList()` zwraca listę tabel
        when(mockSchemaQuery.getResultList()).thenReturn(mockTables);

        // ✅ Mockowanie parametrów zapytań UPDATE
        when(mockUpdateQuery.setParameter(anyString(), any())).thenReturn(mockUpdateQuery);
        when(mockUpdateQuery.executeUpdate()).thenReturn(1); // Symulujemy poprawną aktualizację

        // 🔹 **Uruchamiamy metodę, którą testujemy**
        professorCleanupService.removeProfessorReferences(professorId);

        // ✅ 1. Sprawdzamy, czy zapytanie SELECT zostało wywołane
        verify(entityManager, times(1)).createNativeQuery(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'professor_id'");

        // ✅ 2. Sprawdzamy, czy `getResultList()` zostało wywołane na `mockSchemaQuery`
        verify(mockSchemaQuery, times(1)).getResultList();

        // ✅ 3. Sprawdzamy, czy dla każdej tabeli wywołano `UPDATE`
        for (String table : mockTables) {
            verify(entityManager, times(1)).createNativeQuery("UPDATE " + table + " SET professor_id = NULL WHERE professor_id = :id");
        }

        // ✅ 4. Sprawdzamy, czy zapytania UPDATE wykonały się poprawnie
        verify(mockUpdateQuery, times(mockTables.size())).setParameter("id", professorId);
        verify(mockUpdateQuery, times(mockTables.size())).executeUpdate();
    }
}

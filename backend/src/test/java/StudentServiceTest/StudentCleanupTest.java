package StudentServiceTest;

import com.example.backend.service.studentService.StudentCleanupService;
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

public class StudentCleanupTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private StudentCleanupService studentCleanupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRemoveStudentReferences() {
        List<String> tables = List.of("grades", "final_grades", "petitions");
        when(entityManager.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'student_id'"))
                .thenReturn(query);
        when(query.getResultList()).thenReturn(tables);

        when(entityManager.createNativeQuery(any(String.class))).thenReturn(query);
        when(query.setParameter(eq("id"), any(Long.class))).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        Long studentId = 1L;
        studentCleanupService.removeStudentReferences(studentId);

        verify(entityManager, times(1)).createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'student_id'");

        for (String table : tables) {
            verify(entityManager, times(1)).createNativeQuery("UPDATE " + table + " SET student_id = NULL WHERE student_id = :id");
        }

        verify(query, times(tables.size())).setParameter(eq("id"), eq(studentId));
        verify(query, times(tables.size())).executeUpdate();
    }
}

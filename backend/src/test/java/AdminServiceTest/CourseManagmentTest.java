package AdminServiceTest;

import com.example.backend.model.Course;
import com.example.backend.repository.CourseRepository;
import com.example.backend.service.adminService.CourseManagementForAdminService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CourseManagementForAdminServiceTest {

    @InjectMocks
    private CourseManagementForAdminService courseManagementForAdminService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCourse() {
        // Given
        List<Long> studentIDs = Arrays.asList(1L, 7L, 8L, 9L);
        Course course = new Course(5L, "Anal 101", 5, 1L, "1", "Femboys", studentIDs);

        // When
        courseManagementForAdminService.createCourse(course);

        // Then
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testDeleteCourse_Success() {
        // Given
        Long courseId = 1L;
        when(courseRepository.existsById(courseId)).thenReturn(true);

        List<String> tables = List.of("major_group", "final_grades", "exams");
        when(entityManager.createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'course_id'"))
                .thenReturn(query);
        when(query.getResultList()).thenReturn(tables);

        when(entityManager.createNativeQuery(any(String.class))).thenReturn(query);
        when(query.setParameter(eq("id"), any(Long.class))).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        // When
        courseManagementForAdminService.deleteCourse(courseId);

        // Then
        verify(entityManager, times(1)).createNativeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'course_id'");

        for (String table : tables) {
            verify(entityManager, times(1)).createNativeQuery("UPDATE " + table + " SET course_id = NULL WHERE course_id = :id");
        }

        verify(query, times(tables.size())).setParameter(eq("id"), eq(courseId));
        verify(query, times(tables.size())).executeUpdate();

        verify(courseRepository, times(1)).deleteById(courseId);
    }

    @Test
    void testDeleteCourse_CourseNotFound() {
        // Given
        Long courseId = 1L;
        when(courseRepository.existsById(courseId)).thenReturn(false);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> courseManagementForAdminService.deleteCourse(courseId));
        assert exception.getMessage().equals("Course with ID " + courseId + " not found.");

        verify(entityManager, never()).createNativeQuery(anyString());
        verify(courseRepository, never()).deleteById(anyLong());
    }
}

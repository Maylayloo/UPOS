package ProffesorServiceTest;

import com.example.backend.model.Course;
import com.example.backend.repository.CourseRepository;
import com.example.backend.service.professorService.CourseManagementForProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CourseManagementForProfessorServiceTest {

    @InjectMocks
    private CourseManagementForProfessorService courseManagementForProfessorService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCourses() {
        // Given
        Long professorId = 1L;
        List<Course> mockCourses = Arrays.asList(
                new Course(1L, "Mathematics", 5, "Hugh Janus", "5", "Math"),
                new Course(2L, "Physics", 4, "Ben Dover", "3", "Science")
        );

        when(courseRepository.findByProfessorId(professorId)).thenReturn(mockCourses);

        // When
        List<Course> courses = courseManagementForProfessorService.getCourses(professorId);

        // Then
        assertEquals(2, courses.size());
        assertEquals("Mathematics", courses.get(0).getName());
        assertEquals("Physics", courses.get(1).getName());
        verify(courseRepository, times(1)).findByProfessorId(professorId);
    }
}

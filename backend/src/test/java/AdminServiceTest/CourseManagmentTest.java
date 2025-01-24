package AdminServiceTest;


import com.example.backend.model.Course;
import com.example.backend.repository.CourseRepository;
import com.example.backend.service.adminService.CourseManagementForAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CourseManagementForAdminServiceTest {

    @InjectMocks
    private CourseManagementForAdminService courseManagementForAdminService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCourse() {
        // Given
        List<Long> studentIDS= Arrays.asList(1L,7L,8L,9L);
        Course course = new Course(5L, "Anal 101", 5, 1L, "1", "Femboys", studentIDS);

        // When
        courseManagementForAdminService.createCourse(course);

        // Then
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testDeleteCourse() {
        // Given
        Long courseId = 1L;

        // When
        courseManagementForAdminService.deleteCourse(courseId);

        // Then
        verify(courseRepository, times(1)).deleteById(courseId);
    }
}

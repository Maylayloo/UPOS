package AdminServiceTest;


import com.example.backend.model.Course;
import com.example.backend.repository.CourseRepository;
import com.example.backend.service.adminService.CourseManagementForAdminService;
import io.netty.handler.codec.string.LineSeparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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
        List<Long> studentsId = new ArrayList<Long>(Arrays.asList(1L, 2L));
        Course course = new Course(5L, "Anal 101", 5, 1L, "1", "Femboys",studentsId);

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

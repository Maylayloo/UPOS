package StudentServiceTest;

import com.example.backend.model.Course;
import com.example.backend.model.MajorGroup;
import com.example.backend.model.Student;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.MajorGroupRepository;
import com.example.backend.service.studentService.CourseForStudentService;
import com.example.backend.service.studentService.StudentAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CourseForStudentServiceTest {

    @InjectMocks
    private CourseForStudentService courseForStudentService;

    @Mock
    private MajorGroupRepository majorGroupRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentAuthenticationService studentAuthenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMajorGroupsByLoggedInStudent() {
        // Given
        Student loggedInStudent = new Student();
        loggedInStudent.setStudentId(1L);
        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(loggedInStudent);

        List<MajorGroup> mockGroups = Arrays.asList(
                new MajorGroup(1L, "Lecture", 1, null, null, null, "Room 101", 30, List.of(1L)),
                new MajorGroup(2L, "Lab", 2, null, null, null, "Room 102", 25, List.of(1L))
        );
        when(majorGroupRepository.findAll()).thenReturn(mockGroups);

        // When
        List<MajorGroup> result = courseForStudentService.getMajorGroupsByLoggedInStudent();

        // Then
        assertEquals(2, result.size());
        assertEquals("Lecture", result.get(0).getType());
        assertEquals("Lab", result.get(1).getType());
        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(majorGroupRepository, times(1)).findAll();
    }

    @Test
    void testGetMajorGroupsByLoggedInStudentAndCourseId() {
        // Given
        Student loggedInStudent = new Student();
        loggedInStudent.setStudentId(1L);
        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(loggedInStudent);

        Long courseId = 101L;

        // Mocking the course existence

        List<Long> studentIDS= Arrays.asList(1L,7L,8L,9L);
        Course mockCourse = new Course(courseId, "Mathematics", 5, 1L, "5", "Math",studentIDS);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(mockCourse));

        // Mocking the groups related to the course
        List<MajorGroup> mockGroups = Arrays.asList(
                new MajorGroup(1L, "Lecture", 1, null, null, null, "Room 101", 30, List.of(1L)),
                new MajorGroup(2L, "Lab", 2, null, null, null, "Room 102", 25, List.of(1L))
        );
        when(majorGroupRepository.findByCourseId(courseId)).thenReturn(mockGroups);

        // When
        List<MajorGroup> result = courseForStudentService.getMajorGroupsByLoggedInStudentAndCourseId(courseId);

        // Then
        assertEquals(2, result.size());
        assertEquals("Lecture", result.get(0).getType());
        assertEquals("Lab", result.get(1).getType());
        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(courseRepository, times(1)).findById(courseId);
        verify(majorGroupRepository, times(1)).findByCourseId(courseId);
    }

    @Test
    void testGetCoursesByLoggedInStudent() {
        // Given
        Student loggedInStudent = new Student();
        loggedInStudent.setStudentId(1L);
        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(loggedInStudent);

        List<MajorGroup> mockGroups = Arrays.asList(
                new MajorGroup(1L, "Lecture", 1, null, null, null, "Room 101", 30, List.of(1L)),
                new MajorGroup(2L, "Lab", 2, null, null, null, "Room 102", 25, List.of(1L))
        );
        when(majorGroupRepository.findAll()).thenReturn(mockGroups);

        List<Long> studentIDS= Arrays.asList(1L,7L,8L,9L);
        List<Course> mockCourses = Arrays.asList(
                new Course(101L, "Mathematics", 5, 1L, "5", "Math",studentIDS),
                new Course(102L, "Physics", 4, 1L, "4", "Science",studentIDS)

        );
        when(courseRepository.findAllById(anyList())).thenReturn(mockCourses);

        // When
        List<Course> result = courseForStudentService.getCoursesByLoggedInStudent();

        // Then
        assertEquals(2, result.size());
        assertEquals("Mathematics", result.get(0).getName());
        assertEquals("Physics", result.get(1).getName());
        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(majorGroupRepository, times(1)).findAll();
        verify(courseRepository, times(1)).findAllById(anyList());
    }
}

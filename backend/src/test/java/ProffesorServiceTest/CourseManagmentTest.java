package ProffesorServiceTest;

import com.example.backend.model.Course;
import com.example.backend.model.DayOfTheWeek;
import com.example.backend.model.MajorGroup;
import com.example.backend.model.Professor;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.MajorGroupRepository;
import com.example.backend.service.professorService.CourseManagementForProfessorService;
import com.example.backend.service.professorService.ProfessorAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CourseManagementForProfessorServiceTest {

    @InjectMocks
    private CourseManagementForProfessorService courseManagementForProfessorService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private MajorGroupRepository majorGroupRepository;

    @Mock
    private ProfessorAuthenticationService professorAuthenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCourses() {
        // Given
        Long professorId = 1L;
        List<Course> mockCourses = Arrays.asList(
                new Course(1L, "Mathematics", 5, 1L, "5", "Math"),
                new Course(2L, "Physics", 4, 3L, "3", "Science")
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

    @Test
    void testGetMajorGroupsByLoggedInProfessor() {
        // Given
        Professor mockProfessor = new Professor();
        mockProfessor.setProfessorId(1L);

        List<Course> mockCourses = Arrays.asList(
                new Course(1L, "Mathematics", 5, 1L, "5", "Math"),
                new Course(2L, "Physics", 4, 1L, "3", "Science")
        );

        List<MajorGroup> mockMajorGroups = Arrays.asList(
                new MajorGroup(
                        1L, "Lab", 1, DayOfTheWeek.PONIEDZIAŁEK,
                        LocalTime.of(8, 0), LocalTime.of(10, 0),
                        "Room 101", 30, Arrays.asList(1L, 2L, 3L)
                ),
                new MajorGroup(
                        2L, "Lab", 1, DayOfTheWeek.PIĄTEK,
                        LocalTime.of(8, 0), LocalTime.of(10, 0),
                        "Room 102", 30, Arrays.asList(1L, 2L, 3L)
                )
        );

        when(professorAuthenticationService.getLoggedInProfessor()).thenReturn(mockProfessor);
        when(courseRepository.findByProfessorId(1L)).thenReturn(mockCourses);
        when(majorGroupRepository.findByCourseId(1L)).thenReturn(Collections.singletonList(mockMajorGroups.get(0)));
        when(majorGroupRepository.findByCourseId(2L)).thenReturn(Collections.singletonList(mockMajorGroups.get(1)));

        // When
        List<MajorGroup> result = courseManagementForProfessorService.getMajorGroupsByLoggedInProfessor();

        // Then
        assertEquals(2, result.size());
        verify(professorAuthenticationService, times(1)).getLoggedInProfessor();
        verify(courseRepository, times(1)).findByProfessorId(1L);
        verify(majorGroupRepository, times(1)).findByCourseId(1L);
        verify(majorGroupRepository, times(1)).findByCourseId(2L);
    }

    @Test
    void testGetMajorGroupsByLoggedInProfessorAndCourseId_Success() {
        // Given
        Professor mockProfessor = new Professor();
        mockProfessor.setProfessorId(1L);

        Course mockCourse = new Course(1L, "Mathematics", 5, 1L, "5", "Math");

        List<MajorGroup> mockMajorGroups = Arrays.asList(
                new MajorGroup(
                        1L, "Lab", 1, DayOfTheWeek.PONIEDZIAŁEK,
                        LocalTime.of(8, 0), LocalTime.of(10, 0),
                        "Room 101", 30, Arrays.asList(1L, 2L, 3L)
                ),
                new MajorGroup(
                        2L, "Cw Audytoryjne", 1, DayOfTheWeek.PONIEDZIAŁEK,
                        LocalTime.of(15, 0), LocalTime.of(16, 30),
                        "Room 102", 30, Arrays.asList(1L, 2L, 3L)
                )
        );

        when(professorAuthenticationService.getLoggedInProfessor()).thenReturn(mockProfessor);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(mockCourse));
        when(majorGroupRepository.findByCourseId(1L)).thenReturn(mockMajorGroups);

        // When
        List<MajorGroup> result = courseManagementForProfessorService.getMajorGroupsByLoggedInProfessorAndCourseId(1L);

        // Then
        assertEquals(2, result.size());
        verify(professorAuthenticationService, times(1)).getLoggedInProfessor();
        verify(courseRepository, times(1)).findById(1L);
        verify(majorGroupRepository, times(1)).findByCourseId(1L);
    }

    @Test
    void testGetMajorGroupsByLoggedInProfessorAndCourseId_NotOwned() {
        // Given
        Professor mockProfessor = new Professor();
        mockProfessor.setProfessorId(1L);

        Course mockCourse = new Course(1L, "Mathematics", 5, 2L, "5", "Math"); // Professor ID does not match

        when(professorAuthenticationService.getLoggedInProfessor()).thenReturn(mockProfessor);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(mockCourse));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                courseManagementForProfessorService.getMajorGroupsByLoggedInProfessorAndCourseId(1L));

        assertEquals("Logged-in professor does not own this course", exception.getMessage());
        verify(professorAuthenticationService, times(1)).getLoggedInProfessor();
        verify(courseRepository, times(1)).findById(1L);
        verify(majorGroupRepository, never()).findByCourseId(anyLong());
    }

    @Test
    void testGetCoursesByLoggedInProfessor() {
        // Given
        Professor mockProfessor = new Professor();
        mockProfessor.setProfessorId(1L);

        List<Course> mockCourses = Arrays.asList(
                new Course(1L, "Mathematics", 5, 1L, "5", "Math"),
                new Course(2L, "Physics", 4, 1L, "3", "Science")
        );

        when(professorAuthenticationService.getLoggedInProfessor()).thenReturn(mockProfessor);
        when(courseRepository.findByProfessorId(1L)).thenReturn(mockCourses);

        // When
        List<Course> result = courseManagementForProfessorService.getCoursesByLoggedInProfessor();

        // Then
        assertEquals(2, result.size());
        verify(professorAuthenticationService, times(1)).getLoggedInProfessor();
        verify(courseRepository, times(1)).findByProfessorId(1L);
    }
}

package AdminServiceTest;

import com.example.backend.dto.CourseDTO;
import com.example.backend.mapper.CourseMapper;
import com.example.backend.model.Course;
import com.example.backend.model.Student;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.adminService.CourseManagementForAdminService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
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

    @Mock
    private StudentRepository studentRepository;

    @Spy
    private CourseMapper courseMapper;

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

    @Test
    void testCreateCourse_WithEligibleStudents_Succes() {
        CourseDTO mockCourseDTO = new CourseDTO();
        mockCourseDTO.setName("Anal 101");
        mockCourseDTO.setEcts(5);
        mockCourseDTO.setProfessorId(1L);
        mockCourseDTO.setSemester("1");
        mockCourseDTO.setMajor("Femboys");

        Student s1 = new Student("student1@example.com", "password", "Dill", "Doe", "STUDENT",
                "S12345", "Engineering", "Femboys", "2000-01-01",
                "123456789", 1);
        Student s2 = new Student("student2@example.com", "password", "Drill", "Doe", "STUDENT",
                "S12346", "Engineering", "Femboys", "2000-01-02",
                "123456780", 1);
        Student s3 = new Student("student3@example.com", "password", "Phil", "Doe", "STUDENT",
                "S12347", "Engineering", "Femboys", "2000-01-03",
                "123456781", 1);
        Student s4 = new Student("student4@example.com", "password", "Will", "Doe", "STUDENT",
                "S12348", "Engineering", "Femboys", "2000-01-04",
                "123456782", 1);

        s1.setStudentId(1L);
        s2.setStudentId(7L);
        s3.setStudentId(8L);
        s4.setStudentId(9L);
        List<Student> courseStudents = Arrays.asList(s1, s2, s3, s4);
        List<Long> expectedStudentIds = courseStudents.stream()
                .map(Student::getStudentId)
                .collect(Collectors.toList());

        when(studentRepository.findStudentsBySemesterAndMajor(
                Integer.parseInt(mockCourseDTO.getSemester()), mockCourseDTO.getMajor()))
                .thenReturn(courseStudents);


        courseManagementForAdminService.createCourse(mockCourseDTO);

        // capture saved course
        ArgumentCaptor<Course> courseCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository).save(courseCaptor.capture());
        Course savedCourse = courseCaptor.getValue();

        assertEquals("Anal 101", savedCourse.getName());
        assertEquals(5, savedCourse.getEcts());
        assertEquals("1", savedCourse.getSemester());
        assertEquals("Femboys", savedCourse.getMajor());
        assertEquals(expectedStudentIds, savedCourse.getStudentsIds());

        verify(studentRepository, times(1)).findStudentsBySemesterAndMajor(
                Integer.parseInt(mockCourseDTO.getSemester()), mockCourseDTO.getMajor());
        verify(courseRepository, times(1)).save(any(Course.class));
    }


    @Test
    void testCreateCourse_NoEligibleStudents() {
        CourseDTO mockCourseDTO = new CourseDTO();
        mockCourseDTO.setName("Mathematics");
        mockCourseDTO.setEcts(5);
        mockCourseDTO.setProfessorId(1L);
        mockCourseDTO.setSemester("3");
        mockCourseDTO.setMajor("Computer Science");

        when(studentRepository.findStudentsBySemesterAndMajor(
                Integer.parseInt(mockCourseDTO.getSemester()), mockCourseDTO.getMajor()))
                .thenReturn(List.of()); //No students

        courseManagementForAdminService.createCourse(mockCourseDTO);

        ArgumentCaptor<Course> courseCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository).save(courseCaptor.capture());
        Course savedCourse = courseCaptor.getValue();

        assertEquals("Mathematics", savedCourse.getName());
        assertEquals(5, savedCourse.getEcts());
        assertEquals("3", savedCourse.getSemester());
        assertEquals("Computer Science", savedCourse.getMajor());
        assertTrue(savedCourse.getStudentsIds().isEmpty(), "List of students should be empty");

        verify(studentRepository, times(1)).findStudentsBySemesterAndMajor(
                Integer.parseInt(mockCourseDTO.getSemester()), mockCourseDTO.getMajor());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

}

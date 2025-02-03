package AdminServiceTest;

import com.example.backend.model.*;
import com.example.backend.repository.*;
import com.example.backend.service.adminService.UserManagementForAdminService;
import com.example.backend.service.professorService.ProfessorCleanupService;
import com.example.backend.service.professorService.ProfessorService;
import com.example.backend.service.studentService.StudentCleanupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManagementForAdminServiceTest {

    @InjectMocks
    private UserManagementForAdminService userManagementForAdminService;

    @Mock
    private ProfessorCleanupService professorCleanupService;

    @Mock
    private StudentCleanupService studentCleanupService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock MajorGroupRepository majorGroupRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterStudent() {
        Student student = new Student(
                "JacaPlaca@studet.agh.edu.pl",
                "WompWomp",
                "Mike",
                "Oxlong",
                "Student",
                "12345",
                "Engineering",
                "Computer Science",
                "2004-01-01",
                "123456789",
                2
        );

        userManagementForAdminService.registerUser(student);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testRegisterProfessor() {
        Professor professor = new Professor(
                "kap@agh.edu.pl",
                "ICykWaruneczek",
                "Dr. Kaplica",
                "Wielki",
                "Professor",
                1L,
                "Math"
        );

        userManagementForAdminService.registerUser(professor);

        verify(professorRepository, times(1)).save(professor);
    }



    @Test
    void testKillProfessor_Success() {
        // Given
        Long professorId = 1L;
        when(professorRepository.existsById(professorId)).thenReturn(true);

        // When
        userManagementForAdminService.killProfessor(professorId);

        // Then
        verify(professorCleanupService, times(1)).removeProfessorReferences(professorId);
        verify(professorRepository, times(1)).deleteById(professorId);
    }

    @Test
    void testKillProfessor_ProfessorNotFound() {
        // Given
        Long professorId = 2L;
        when(professorRepository.existsById(professorId)).thenReturn(false);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                userManagementForAdminService.killProfessor(professorId));

        assertEquals("Professor with ID 2 not found.", exception.getMessage());
        verify(professorCleanupService, never()).removeProfessorReferences(anyLong());
        verify(professorRepository, never()).deleteById(anyLong());
    }


    @Test
    void testKillStudent_Success() {
        // Given
        Long studentId = 1L;

        Course course1 = new Course(1L, "Mathematics", 5, 101L, "3", "CS", new ArrayList<>(List.of(1L, 2L, 3L)));
        Course course2 = new Course(2L, "Physics", 4, 102L, "3", "CS", new ArrayList<>(List.of(1L, 4L, 5L)));

        MajorGroup group1 = new MajorGroup("Lab", 1, DayOfTheWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0),
                "Room 101", 30, new ArrayList<>(List.of(1L, 6L, 7L)));

        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(courseRepository.findCoursesByStudentId(studentId)).thenReturn(List.of(course1, course2));
        when(majorGroupRepository.findMajorGroupsByStudentId(studentId)).thenReturn(List.of(group1));

        // When
        userManagementForAdminService.killStudent(studentId);

        // Then
        assertFalse(course1.getStudentsIds().contains(studentId));
        assertFalse(course2.getStudentsIds().contains(studentId));
        verify(courseRepository, times(1)).save(course1);
        verify(courseRepository, times(1)).save(course2);

        assertFalse(group1.getStudentsIds().contains(studentId));
        verify(majorGroupRepository, times(1)).save(group1);

        verify(studentCleanupService, times(1)).removeStudentReferences(studentId);
        verify(studentRepository, times(1)).deleteById(studentId);
    }

    @Test
    void testKillStudent_StudentNotFound() {
        // Given
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(false);

        // When & Then
        try {
            userManagementForAdminService.killStudent(studentId);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals("Student with ID " + studentId + " not found."));
        }

        verify(studentCleanupService, never()).removeStudentReferences(anyLong());
        verify(studentRepository, never()).deleteById(anyLong());


        verify(courseRepository, never()).findCoursesByStudentId(anyLong());
        verify(majorGroupRepository, never()).findMajorGroupsByStudentId(anyLong());
        verify(courseRepository, never()).save(any(Course.class));
        verify(majorGroupRepository, never()).save(any(MajorGroup.class));
    }
}

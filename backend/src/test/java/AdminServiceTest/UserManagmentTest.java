package AdminServiceTest;

import com.example.backend.model.*;
import com.example.backend.repository.*;
import com.example.backend.service.adminService.UserManagementForAdminService;
import com.example.backend.service.professorService.ProfessorCleanupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserManagementForAdminServiceTest {

    @InjectMocks
    private UserManagementForAdminService userManagementForAdminService;

    @Mock
    private ProfessorCleanupService professorCleanupService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ExamRepository examRepository;

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

    /** ❌ Test sprawdzający, czy rzucany jest wyjątek, gdy profesor nie istnieje */
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
    void testKillStudent() {
        Long studentId = 1L;

        userManagementForAdminService.killStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }
}

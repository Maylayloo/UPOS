package AdminServiceTest;

import com.example.backend.model.Student;
import com.example.backend.model.Professor;
import com.example.backend.repository.StudentRepository;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.service.adminService.UserManagementForAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UserManagementForAdminServiceTest {

    @InjectMocks
    private UserManagementForAdminService userManagementForAdminService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ProfessorRepository professorRepository;

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
    void testKillProfessor() {
        Long professorId = 1L;

        userManagementForAdminService.killProfessor(professorId);

        verify(professorRepository, times(1)).deleteById(professorId);
    }


    @Test
    void testKillStudent() {
        Long studentId = 1L;

        userManagementForAdminService.killStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }
}

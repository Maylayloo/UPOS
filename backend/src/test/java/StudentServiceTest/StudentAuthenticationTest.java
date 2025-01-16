package StudentServiceTest;

import com.example.backend.model.MyUser;
import com.example.backend.model.Role;
import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.studentService.StudentAuthenticationService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentAuthenticationServiceTest {

    @InjectMocks
    private StudentAuthenticationService studentAuthenticationService;

    @Mock
    private UserWebAuthenticationService userWebAuthenticationService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLoggedInStudent_Success() {
        // Given
        MyUser loggedInUser = new MyUser();
        loggedInUser.setUserId(1L);
        loggedInUser.setRole(Role.STUDENT);

        Student student = new Student();
        student.setStudentId(1L);
        student.setUser(loggedInUser);

        when(userWebAuthenticationService.getLoggedInUser()).thenReturn(loggedInUser);
        when(studentRepository.findByUser_UserId(loggedInUser.getUserId())).thenReturn(Optional.of(student));

        // When
        Student result = studentAuthenticationService.getLoggedInStudent();

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getStudentId());
        assertEquals(loggedInUser, result.getUser());
        verify(userWebAuthenticationService, times(1)).getLoggedInUser();
        verify(studentRepository, times(1)).findByUser_UserId(loggedInUser.getUserId());
    }

    @Test
    void testGetLoggedInStudent_NotStudentRole() {
        // Given
        MyUser loggedInUser = new MyUser();
        loggedInUser.setUserId(1L);
        loggedInUser.setRole(Role.PROFESSOR); // Role is not STUDENT

        when(userWebAuthenticationService.getLoggedInUser()).thenReturn(loggedInUser);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> studentAuthenticationService.getLoggedInStudent());

        assertEquals("Logged-in user is not a student", exception.getMessage());
        verify(userWebAuthenticationService, times(1)).getLoggedInUser();
        verifyNoInteractions(studentRepository);
    }

    @Test
    void testGetLoggedInStudent_StudentNotFound() {
        // Given
        MyUser loggedInUser = new MyUser();
        loggedInUser.setUserId(1L);
        loggedInUser.setRole(Role.STUDENT);

        when(userWebAuthenticationService.getLoggedInUser()).thenReturn(loggedInUser);
        when(studentRepository.findByUser_UserId(loggedInUser.getUserId())).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> studentAuthenticationService.getLoggedInStudent());

        assertEquals("Could not find student with userId: 1", exception.getMessage());
        verify(userWebAuthenticationService, times(1)).getLoggedInUser();
        verify(studentRepository, times(1)).findByUser_UserId(loggedInUser.getUserId());
    }
}

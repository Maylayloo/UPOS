package ProffesorServiceTest;

import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;
import com.example.backend.model.Role;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.service.professorService.ProfessorAuthenticationService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProfessorAuthenticationServiceTest {

    @InjectMocks
    private ProfessorAuthenticationService professorAuthenticationService;

    @Mock
    private UserWebAuthenticationService userWebAuthenticationService;

    @Mock
    private ProfessorRepository professorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLoggedInProfessor_Success() {
        // Given
        MyUser mockUser = new MyUser("professor@example.com", "password", "Dill", "Doe", Role.PROFESSOR, "738439234", "krysztalowa 69, Kraków 30-072", "232343545432435235");
        mockUser.setUserId(1L);

        Professor mockProfessor = new Professor();
        mockProfessor.setProfessorId(1L);
        mockProfessor.setUserId(mockUser.getUserId());

        when(userWebAuthenticationService.getLoggedInUser()).thenReturn(mockUser);
        when(professorRepository.findByUserId(1L)).thenReturn(Optional.of(mockProfessor));

        // When
        Professor result = professorAuthenticationService.getLoggedInProfessor();

        // Then
        assertEquals(mockProfessor, result);
        verify(userWebAuthenticationService, times(1)).getLoggedInUser();
        verify(professorRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetLoggedInProfessor_UserNotProfessor() {
        // Given
        MyUser mockUser = new MyUser("user@example.com", "password", "Jane", "Doe", Role.STUDENT, "735689234", "krysztalowa 420, Kraków 30-072", "232343545432435235");
        mockUser.setUserId(2L);

        when(userWebAuthenticationService.getLoggedInUser()).thenReturn(mockUser);
        when(professorRepository.findByUserId(2L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                professorAuthenticationService.getLoggedInProfessor());

        assertEquals("Logged-in user is not a professor", exception.getMessage());
        verify(userWebAuthenticationService, times(1)).getLoggedInUser();
        verify(professorRepository, times(1)).findByUserId(2L);
    }
}

package UserServiceTest;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.userService.UserDataManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDataManagementServiceTest {

    @InjectMocks
    private UserDataManagementService userDataManagementService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserByEmail_UserExists() {
        // Given
        String email = "john.doe@example.com";
        User mockUser = new User(email, "securepassword", "John", "Doe", "User");
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        // When
        Optional<User> result = userDataManagementService.getUserByEmail(email);

        // Then
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        assertEquals("John", result.get().getName());
        assertEquals("Doe", result.get().getSurname());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetUserByEmail_UserDoesNotExist() {
        // Given
        String email = "non.existent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When
        Optional<User> result = userDataManagementService.getUserByEmail(email);

        // Then
        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findByEmail(email);
    }
}

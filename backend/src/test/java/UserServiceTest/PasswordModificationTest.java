package UserServiceTest;


import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.userService.UserModificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserModificationServiceTest {

    @InjectMocks
    private UserModificationService userModificationService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testChangePassword_UserExists() {
        // Given
        Long userId = 1L;
        String newPassword = "123new";
        User mockUser = new User("Dill.doe@example.com", "oldPassword", "Dill", "Doe", "User");
        mockUser.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // When
        boolean result = userModificationService.changePassword(userId, newPassword);

        // Then
        assertTrue(result);
        assertEquals(newPassword, mockUser.getPassword());
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testChangePassword_UserDoesNotExist() {
        // Given
        Long userId = 1L;
        String newPassword = "newPassword";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When
        boolean result = userModificationService.changePassword(userId, newPassword);

        // Then
        assertFalse(result);
        verify(userRepository, never()).save(any(User.class));
    }
}


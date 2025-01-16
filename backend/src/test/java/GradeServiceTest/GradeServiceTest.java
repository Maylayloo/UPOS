package GradeServiceTest;


import com.example.backend.model.Grade;
import com.example.backend.model.MyUser;
import com.example.backend.model.Role;
import com.example.backend.model.Student;
import com.example.backend.repository.GradeRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.gradeService.GradeService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GradeServiceTest {

    @InjectMocks
    private GradeService gradeService;

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private UserWebAuthenticationService userWebAuthenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowGradesByStudentId() {
        // Given
        Long studentId = 1L;
        List<Grade> mockGrades = List.of(
                new Grade(studentId, 101L, true, "A"),
                new Grade(studentId, 102L, false, "B")
        );

        when(gradeRepository.findByStudentId(studentId)).thenReturn(mockGrades);

        // When
        List<Grade> grades = gradeService.showGradesByStudentId(studentId);

        // Then
        assertEquals(2, grades.size());
        assertEquals("A", grades.get(0).getValue());
        assertEquals("B", grades.get(1).getValue());
        verify(gradeRepository, times(1)).findByStudentId(studentId);
    }


    @Test
    void testShowAllGradesByLoggedInStudent_Success() {
        // Given
        MyUser loggedInUser = new MyUser();
        loggedInUser.setUserId(1L);
        loggedInUser.setRole(Role.STUDENT);

        Student student = new Student();
        student.setStudentId(1L);
        student.setUser(loggedInUser);

        List<Grade> mockGrades = List.of(
                new Grade(1L, student.getStudentId(), 101L, true, "A"),
                new Grade(2L, student.getStudentId(), 102L, false, "B")
        );

        when(userWebAuthenticationService.getLoggedInUser()).thenReturn(loggedInUser);
        when(studentRepository.findByUser_UserId(loggedInUser.getUserId())).thenReturn(Optional.of(student));
        when(gradeRepository.findByStudentId(student.getStudentId())).thenReturn(mockGrades);

        // When
        List<Grade> result = gradeService.showAllGradesByLoggedInStudent();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getValue());
        assertEquals("B", result.get(1).getValue());

        verify(userWebAuthenticationService, times(1)).getLoggedInUser();
        verify(studentRepository, times(1)).findByUser_UserId(loggedInUser.getUserId());
        verify(gradeRepository, times(1)).findByStudentId(student.getStudentId());
    }

    @Test
    void testShowAllGradesByLoggedInStudent_NotStudent() {
        // Given
        MyUser loggedInUser = new MyUser();
        loggedInUser.setUserId(1L);
        loggedInUser.setRole(Role.PROFESSOR); // Role is not STUDENT

        when(userWebAuthenticationService.getLoggedInUser()).thenReturn(loggedInUser);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> gradeService.showAllGradesByLoggedInStudent());

        assertEquals("Logged-in user is not a student", exception.getMessage());
        verify(userWebAuthenticationService, times(1)).getLoggedInUser();
        verifyNoInteractions(studentRepository);
        verifyNoInteractions(gradeRepository);
    }

    @Test
    void testShowAllGradesByLoggedInStudent_StudentNotFound() {
        // Given
        MyUser loggedInUser = new MyUser();
        loggedInUser.setUserId(1L);
        loggedInUser.setRole(Role.STUDENT);

        when(userWebAuthenticationService.getLoggedInUser()).thenReturn(loggedInUser);
        when(studentRepository.findByUser_UserId(loggedInUser.getUserId())).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> gradeService.showAllGradesByLoggedInStudent());

        assertEquals("Could not find student with userId: 1", exception.getMessage());
        verify(userWebAuthenticationService, times(1)).getLoggedInUser();
        verify(studentRepository, times(1)).findByUser_UserId(loggedInUser.getUserId());
        verifyNoInteractions(gradeRepository);
    }

    @Test
    void testShowGradesByGradeId_GradesFound() {
        // Given
        Long gradeId = 1L;
        List<Grade> mockGrades = List.of(new Grade(1L, 101L, true, "A"));
        when(gradeRepository.findByGradeId(gradeId)).thenReturn(mockGrades);

        // When
        List<Grade> grades = gradeService.showGradesByGradeId(gradeId);

        // Then
        assertNotNull(grades);
        assertEquals(1, grades.size());
        assertEquals("A", grades.get(0).getValue());
        verify(gradeRepository, times(1)).findByGradeId(gradeId);
    }

    @Test
    void testShowGradesByGradeId_NoGradesFound() {
        // Given
        Long gradeId = 1L;
        when(gradeRepository.findByGradeId(gradeId)).thenReturn(List.of());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            gradeService.showGradesByGradeId(gradeId);
        });

        assertEquals("No grades found for gradeId: 1", exception.getMessage());
        verify(gradeRepository, times(1)).findByGradeId(gradeId);
    }
}

package ProffesorServiceTest;

import com.example.backend.model.*;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.ExamRepository;
import com.example.backend.service.professorService.ExamManagementForProfessorService;
import com.example.backend.service.professorService.ProfessorAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamManagementForProfessorServiceTest {

    @InjectMocks
    private ExamManagementForProfessorService examManagementForProfessorService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ProfessorAuthenticationService professorAuthenticationService;

    @Mock
    private ExamRepository examRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScheduleExam() {
        // Given
        Long courseId = 1L;

        List<Long> studentIDS= Arrays.asList(1L,7L,8L,9L);
        Course course = new Course(8L,"EIT", 5, 2L, "1", "Math",studentIDS);
        Exam exam = new Exam(2L, 1L,1L, "2025-01-10", "D5-s101");


        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // When
        examManagementForProfessorService.scheduleExam(courseId, exam);

        // Then
        assertEquals(1, course.getExams().size());
        assertEquals(exam, course.getExams().get(0));
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testModifyExamById() {
        // Given
        Long courseId = 6L;
        Long examId = 3L;

        List<Long> studentIDS= Arrays.asList(1L,7L,8L,9L);
        Course course = new Course(6L,"Math", 5, 2L, "2", "AiR", studentIDS);
        Exam oldExam = new Exam(3L,1L, "2025-01-10", "Room 101");

        course.addExam(oldExam);
        Exam updatedExam = new Exam(5L, 1L,2L, "2025-02-15", "Room 202","1");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // When
        examManagementForProfessorService.modifyExamById(courseId, examId, updatedExam);

        // Then
        assertEquals(1, course.getExams().size());
        assertEquals("2025-02-15", course.getExams().get(0).getDate());
        assertEquals("Room 202", course.getExams().get(0).getPlace());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testModifyExamById_ExamNotFound() {
        // Given
        List<Long> studentIDS= Arrays.asList(1L,7L,8L,9L);
        Long courseId = 1L;
        Long examId = 2L; // Exam ID that doesn't exist

        Course course = new Course(7L,"Math", 5, 1L, "3", "Mathematics",studentIDS);
        course.addExam(new Exam(6L,1L, "2025-01-10", "Room 101"));
        Exam updatedExam = new Exam(7L,2L, "2025-02-15", "Room 202");


        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                examManagementForProfessorService.modifyExamById(courseId, examId, updatedExam));

        assertEquals("Exam not found with ID: 2 in course ID: 1", exception.getMessage());
        verify(courseRepository, never()).save(course);
    }

    @Test
    void testGetExamsByLoggedInProfessor_Success() {
        // Given
        MyUser loggedInUser = new MyUser();
        loggedInUser.setUserId(1L);
        loggedInUser.setRole(Role.PROFESSOR);

        Professor professor = new Professor();
        professor.setProfessorId(1L);
        professor.setUserId(loggedInUser.getUserId());

        List<Exam> mockExams = List.of(
                new Exam(6L,1L,1L, "2025-01-10", "Room 101","1"),
                new Exam(7L,1L,1L, "2025-01-20", "Room 202","2")
        );

        when(professorAuthenticationService.getLoggedInProfessor()).thenReturn(professor);
        when(examRepository.findByProfessorId(professor.getProfessorId())).thenReturn(mockExams);

        // When
        List<Exam> result = examManagementForProfessorService.getExamsByLoggedInProfessor();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("2025-01-10", result.get(0).getDate());
        assertEquals("Room 101", result.get(0).getPlace());
        assertEquals("2025-01-20", result.get(1).getDate());
        assertEquals("Room 202", result.get(1).getPlace());

        verify(professorAuthenticationService, times(1)).getLoggedInProfessor();
        verify(examRepository, times(1)).findByProfessorId(professor.getProfessorId());
    }

    @Test
    void testGetExamsByLoggedInProfessor_NoExams() {
        // Given
        MyUser loggedInUser = new MyUser();
        loggedInUser.setUserId(1L);
        loggedInUser.setRole(Role.PROFESSOR);

        Professor professor = new Professor();
        professor.setProfessorId(1L);
        professor.setUserId(loggedInUser.getUserId());

        when(professorAuthenticationService.getLoggedInProfessor()).thenReturn(professor);
        when(examRepository.findByProfessorId(professor.getProfessorId())).thenReturn(List.of());

        // When
        List<Exam> result = examManagementForProfessorService.getExamsByLoggedInProfessor();

        // Then
        assertNotNull(result);
        assertEquals(0, result.size());

        verify(professorAuthenticationService, times(1)).getLoggedInProfessor();
        verify(examRepository, times(1)).findByProfessorId(professor.getProfessorId());
    }

    @Test
    void testGetExamsByLoggedInProfessor_NotLoggedIn() {
        // Given
        when(professorAuthenticationService.getLoggedInProfessor()).thenThrow(new RuntimeException("No professor is currently logged in"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> examManagementForProfessorService.getExamsByLoggedInProfessor());

        assertEquals("No professor is currently logged in", exception.getMessage());

        verify(professorAuthenticationService, times(1)).getLoggedInProfessor();
        verifyNoInteractions(examRepository);
    }

}

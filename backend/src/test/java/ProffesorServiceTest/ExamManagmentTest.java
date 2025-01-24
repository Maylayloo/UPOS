package ProffesorServiceTest;

import com.example.backend.dto.ExamDTO;
import com.example.backend.model.*;
import com.example.backend.repository.CourseRepository;
import com.example.backend.mapper.ExamMapper;
import com.example.backend.repository.ExamRepository;
import com.example.backend.service.professorService.ExamManagementForProfessorService;
import com.example.backend.service.professorService.ProfessorAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
    void testScheduleExamWithCourseId_Success() {
        // Given
        Long courseId = 1L;
        List<Long> studentsId = new ArrayList<Long>(Arrays.asList(1L, 2L));
        Course mockCourse = new Course(courseId, "Mathematics", 5, 101L, "Fall 2025", "Math",studentsId);
        Exam mockExam = new Exam(1L, courseId, 101L, "2025-05-15", "Room A1", "1");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(mockCourse));

        // When
        examManagementForProfessorService.scheduleExam(courseId, mockExam);

        // Then
        verify(courseRepository, times(1)).findById(courseId);
        verify(courseRepository, times(1)).save(mockCourse);
        assertEquals(1, mockCourse.getExams().size());
        assertEquals(mockExam, mockCourse.getExams().get(0));
    }

    @Test
    void testScheduleExamWithCourseId_CourseNotFound() {
        // Given
        Long courseId = 1L;
        Exam mockExam = new Exam(1L, courseId, 101L, "2025-05-15", "Room A1", "1");

        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                examManagementForProfessorService.scheduleExam(courseId, mockExam));

        assertEquals("Course not found with ID: 1", exception.getMessage());
        verify(courseRepository, times(1)).findById(courseId);
        verify(courseRepository, never()).save(any(Course.class));
    }

    //this test don't work propably because of a problem with mapper, shows that there's no instance but there is, idk why
    //to do
    @Test
    void testScheduleExamWithDTO_Success() {
        // Given
        ExamDTO mockExamDTO = new ExamDTO();
        mockExamDTO.setCourseId(1L);
        mockExamDTO.setProfessorId(101L);
        mockExamDTO.setDate("2025-05-15");
        mockExamDTO.setPlace("Room A1");
        mockExamDTO.setAttempt("1");

        //Exam mockExam = ExamMapper.INSTANCE.examDTOToExam(mockExamDTO);
        //this above didn't work

        //this below don't work XD, seems like mapper (used in the prof service) is not seen
        // When
        examManagementForProfessorService.scheduleExam(mockExamDTO);

        // Then
        //this argument captor is
        ArgumentCaptor<Exam> captor = ArgumentCaptor.forClass(Exam.class);
        verify(examRepository, times(1)).save(captor.capture());
        Exam capturedExam = captor.getValue();

        // Assertions to verify that the captured exam matches the DTO
        assertNotNull(capturedExam);
        assertEquals(mockExamDTO.getCourseId(), capturedExam.getCourseId());
        assertEquals(mockExamDTO.getProfessorId(), capturedExam.getProfessorId());
        assertEquals(mockExamDTO.getDate(), capturedExam.getDate());
        assertEquals(mockExamDTO.getPlace(), capturedExam.getPlace());
        assertEquals(mockExamDTO.getAttempt(), capturedExam.getAttempt());
    }

    @Test
    void testModifyExamByIdWithDTO_Success() {
        // Given
        Long examId = 1L;
        Exam existingExam = new Exam(1L, 1L, 101L, "2025-05-10", "Room B2", "1");

        ExamDTO updatedExamDTO = new ExamDTO();
        //updatedExamDTO.setExamId(1L);
        updatedExamDTO.setCourseId(2L);
        updatedExamDTO.setProfessorId(102L);
        updatedExamDTO.setDate("2025-05-15");
        updatedExamDTO.setPlace("Room A1");
        updatedExamDTO.setAttempt("2");

        when(examRepository.findById(examId)).thenReturn(Optional.of(existingExam));

        // When
        examManagementForProfessorService.modifyExamById(examId, updatedExamDTO);

        // Then
        assertEquals("2025-05-15", existingExam.getDate());
        assertEquals("Room A1", existingExam.getPlace());
        assertEquals("2", existingExam.getAttempt());
        assertEquals(2L, existingExam.getCourseId());
        assertEquals(102L, existingExam.getProfessorId());

        verify(examRepository, times(1)).findById(examId);
        verify(examRepository, times(1)).save(existingExam);
    }

    @Test
    void testModifyExamByIdWithDTO_ExamNotFound() {
        // Given
        Long examId = 1L;
        ExamDTO updatedExamDTO = new ExamDTO();

        when(examRepository.findById(examId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                examManagementForProfessorService.modifyExamById(examId, updatedExamDTO));

        assertEquals("Exam with ID 1 not found", exception.getMessage());
        verify(examRepository, times(1)).findById(examId);
        verify(examRepository, never()).save(any(Exam.class));
    }

    @Test
    void testModifyExamByIdWithExam_Success() {
        // Given
        Long examId = 1L;
        Exam existingExam = new Exam(1L, 1L, 101L, "2025-05-10", "Room B2", "1");
        Exam updatedExam = new Exam(1L, 2L, 102L, "2025-05-15", "Room A1", "2");

        when(examRepository.findById(examId)).thenReturn(Optional.of(existingExam));

        // When
        examManagementForProfessorService.modifyExamById(examId, updatedExam);

        // Then
        assertEquals("2025-05-15", existingExam.getDate());
        assertEquals("Room A1", existingExam.getPlace());
        assertEquals("2", existingExam.getAttempt());
        assertEquals(2L, existingExam.getCourseId());
        assertEquals(102L, existingExam.getProfessorId());

        verify(examRepository, times(1)).findById(examId);
        verify(examRepository, times(1)).save(existingExam);
    }

    @Test
    void testModifyExamByIdWithExam_ExamNotFound() {
        // Given
        Long examId = 1L;
        Exam updatedExam = new Exam();

        when(examRepository.findById(examId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                examManagementForProfessorService.modifyExamById(examId, updatedExam));

        assertEquals("Exam with ID 1 not found", exception.getMessage());
        verify(examRepository, times(1)).findById(examId);
        verify(examRepository, never()).save(any(Exam.class));
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

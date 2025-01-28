package GradeServiceTest;

import com.example.backend.model.*;
import com.example.backend.repository.FinalGradeRepository;
import com.example.backend.repository.GradeRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.gradeService.GradeService;
import com.example.backend.service.studentService.StudentAuthenticationService;
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
    private FinalGradeRepository finalGradeRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentAuthenticationService studentAuthenticationService;

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
        Student loggedInStudent = new Student();
        loggedInStudent.setStudentId(1L);

        List<Grade> mockGrades = List.of(
                new Grade(1L, loggedInStudent.getStudentId(), 101L, true, "A"),
                new Grade(2L, loggedInStudent.getStudentId(), 102L, false, "B")
        );

        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(loggedInStudent);
        when(gradeRepository.findByStudentId(loggedInStudent.getStudentId())).thenReturn(mockGrades);

        // When
        List<Grade> result = gradeService.showAllGradesByLoggedInStudent();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getValue());
        assertEquals("B", result.get(1).getValue());

        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(gradeRepository, times(1)).findByStudentId(loggedInStudent.getStudentId());
    }

    @Test
    void testShowAllGradesByLoggedInStudent_StudentNotFound() {
        // Given
        when(studentAuthenticationService.getLoggedInStudent()).thenThrow(new RuntimeException("No student is currently logged in"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> gradeService.showAllGradesByLoggedInStudent());

        assertEquals("No student is currently logged in", exception.getMessage());
        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
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

    @Test
    void testGetAllGradesByLoggedInStudentAndGroupId_Success() {
        // Given
        Long studentId = 1L;
        Long groupId = 101L;
        Student loggedInStudent = new Student();
        loggedInStudent.setStudentId(studentId);

        List<Grade> mockGrades = List.of(
                new Grade(studentId, groupId, true, "A"),
                new Grade(studentId, groupId, false, "B")
        );

        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(loggedInStudent);
        when(gradeRepository.findByStudentIdAndGroupId(studentId, groupId)).thenReturn(mockGrades);

        // When
        List<Grade> result = gradeService.getAllGradesByLoggedInStudentAndGroupId(groupId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getValue());
        assertEquals("B", result.get(1).getValue());
        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(gradeRepository, times(1)).findByStudentIdAndGroupId(studentId, groupId);
    }

    @Test
    void testGetFinalGradeByLoggedInStudentAndCourseId_Success() {
        // Given
        Long studentId = 1L;
        Long courseId = 201L;
        Student loggedInStudent = new Student();
        loggedInStudent.setStudentId(studentId);

        FinalGrade mockFinalGrade = new FinalGrade(studentId, courseId, "A");

        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(loggedInStudent);
        when(finalGradeRepository.findByStudentIdAndCourseId(studentId, courseId)).thenReturn(Optional.of(mockFinalGrade));

        // When
        FinalGrade result = gradeService.getFinalGradeByLoggedInStudentAndCourseId(courseId);

        // Then
        assertNotNull(result);
        assertEquals("A", result.getValue());
        assertEquals(studentId, result.getStudentId());
        assertEquals(courseId, result.getCourseId());
        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(finalGradeRepository, times(1)).findByStudentIdAndCourseId(studentId, courseId);
    }



    @Test
    void testGetFinalGradeByLoggedInStudentAndCourseId_NotFound() {
        // Given
        Long studentId = 1L;
        Long courseId = 201L;
        Student loggedInStudent = new Student();
        loggedInStudent.setStudentId(studentId);

        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(loggedInStudent);
        when(finalGradeRepository.findByStudentIdAndCourseId(studentId, courseId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                gradeService.getFinalGradeByLoggedInStudentAndCourseId(courseId)
        );

        assertEquals("No final grade found for student ID 1 and course ID 201", exception.getMessage());
        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(finalGradeRepository, times(1)).findByStudentIdAndCourseId(studentId, courseId);
    }



    @Test
    void testShowFinalGradeByGradeId_Success() {
        // Given
        Long gradeId = 301L;
        FinalGrade mockFinalGrade = new FinalGrade(1L, 201L, "A");

        when(finalGradeRepository.findById(gradeId)).thenReturn(Optional.of(mockFinalGrade));

        // When
        FinalGrade result = gradeService.showFinalGradeByGradeId(gradeId);

        // Then
        assertNotNull(result);
        assertEquals("A", result.getValue());
        assertEquals(1L, result.getStudentId());
        assertEquals(201L, result.getCourseId());
        verify(finalGradeRepository, times(1)).findById(gradeId);
    }

    @Test
    void testShowFinalGradeByGradeId_NotFound() {
        // Given
        Long gradeId = 301L;

        when(finalGradeRepository.findById(gradeId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                gradeService.showFinalGradeByGradeId(gradeId)
        );

        assertEquals("Final Grade with ID 301 not found", exception.getMessage());
        verify(finalGradeRepository, times(1)).findById(gradeId);
    }



}

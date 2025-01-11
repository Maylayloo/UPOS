package GradeServiceTest;


import com.example.backend.model.Grade;
import com.example.backend.repository.GradeRepository;
import com.example.backend.service.gradeService.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GradeServiceTest {

    @InjectMocks
    private GradeService gradeService;

    @Mock
    private GradeRepository gradeRepository;

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
}

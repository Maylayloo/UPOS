package ProffesorServiceTest;

import com.example.backend.model.Grade;
import com.example.backend.model.FinalGrade;
import com.example.backend.repository.GradeRepository;
import com.example.backend.repository.FinalGradeRepository;
import com.example.backend.service.professorService.GradeManagementForProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class GradeManagementForProfessorServiceTest {

    @InjectMocks
    private GradeManagementForProfessorService gradeManagementForProfessorService;

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private FinalGradeRepository finalGradeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetGradesByStudentId() {
        // Given
        Long studentId = 1L;
        List<Grade> grades = List.of(
                new Grade(studentId, 101L, true, "A"),
                new Grade(studentId, 102L, false, "B")
        );

        // When
        gradeManagementForProfessorService.setGradesByStudentId(studentId, grades);

        // Then
        verify(gradeRepository, times(1)).saveAll(grades);
    }

    @Test
    void testSetFinalGradesByProfessorId() {
        // Given
        Long professorId = 1L;
        List<FinalGrade> finalGrades = List.of(
                new FinalGrade(101L, 1L, "5"),
                new FinalGrade(102L, 1L, "4")
        );

        // When
        gradeManagementForProfessorService.setFinalGradesByProfessorId(professorId, finalGrades);

        // Then
        verify(finalGradeRepository, times(1)).saveAll(finalGrades);
    }

    @Test
    void testDeleteGradeById() {
        // Given
        Long gradeId = 1L;

        // When
        gradeManagementForProfessorService.deleteGradeById(gradeId);

        // Then
        verify(gradeRepository, times(1)).deleteById(gradeId);
    }

    @Test
    void testDeleteFinalGradeByStudentId() {
        // Given
        Long studentId = 1L;

        // When
        gradeManagementForProfessorService.deleteFinalGradeByStudentId(studentId);

        // Then
        verify(finalGradeRepository, times(1)).deleteById(studentId);
    }
}

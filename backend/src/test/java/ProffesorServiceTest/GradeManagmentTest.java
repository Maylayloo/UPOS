package ProffesorServiceTest;

import com.example.backend.dto.GradeRequestDTO;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void testSetFinalGradesByStudentId() {
        // Given
        Long studentId = 1L;
        List<FinalGrade> finalGrades = List.of(
                new FinalGrade(studentId, 101L, "5"),
                new FinalGrade(studentId, 102L, "4")
        );

        // When
        gradeManagementForProfessorService.setFinalGradesByStudentId(studentId, finalGrades);

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
    void testDeleteFinalGrade() {
        // Given
        Long finalGradeId = 1L;

        // When
        gradeManagementForProfessorService.deleteFinalGradeById(finalGradeId);

        // Then
        verify(finalGradeRepository, times(1)).deleteById(finalGradeId);
    }

    @Test
    void testAddGradeByStudentIdAndGroupId() {
        // Given
        GradeRequestDTO gradeRequestDTO = new GradeRequestDTO();
        gradeRequestDTO.setStudentId(1L);
        gradeRequestDTO.setGroupId(101L);
        gradeRequestDTO.setValue("A");
        gradeRequestDTO.setPartial(true);

        Grade grade = new Grade();
        grade.setStudentId(1L);
        grade.setGroupId(101L);
        grade.setValue("A");
        grade.setPartial(true);

        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);

        // When
        gradeManagementForProfessorService.addGradeByStudentIdAndGroupId(gradeRequestDTO);

        // Then
        verify(gradeRepository, times(1)).save(any(Grade.class));
    }

    @Test
    void testUpdateGradeByGradeId_Success() {
        // Given
        Long gradeId = 1L;
        Grade existingGrade = new Grade();
        existingGrade.setGradeId(gradeId);
        existingGrade.setStudentId(1L);
        existingGrade.setGroupId(101L);
        existingGrade.setValue("B");
        existingGrade.setPartial(false);

        GradeRequestDTO gradeRequestDTO = new GradeRequestDTO();
        gradeRequestDTO.setValue("A");
        gradeRequestDTO.setPartial(true);

        when(gradeRepository.findById(gradeId)).thenReturn(Optional.of(existingGrade));

        // When
        gradeManagementForProfessorService.updateGradeByGradeId(gradeId, gradeRequestDTO);

        // Then
        assertEquals("A", existingGrade.getValue());
        assertTrue(existingGrade.isPartial());
        verify(gradeRepository, times(1)).findById(gradeId);
        verify(gradeRepository, times(1)).save(existingGrade);
    }

    @Test
    void testUpdateGradeByGradeId_GradeNotFound() {
        // Given
        Long gradeId = 1L;
        GradeRequestDTO gradeRequestDTO = new GradeRequestDTO();
        gradeRequestDTO.setValue("A");
        gradeRequestDTO.setPartial(true);

        when(gradeRepository.findById(gradeId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                gradeManagementForProfessorService.updateGradeByGradeId(gradeId, gradeRequestDTO));

        assertEquals("Grade with ID 1 not found", exception.getMessage());
        verify(gradeRepository, times(1)).findById(gradeId);
        verify(gradeRepository, never()).save(any());
    }

}

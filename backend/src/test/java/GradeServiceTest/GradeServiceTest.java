package GradeServiceTest;

import com.example.backend.model.Grade;
import com.example.backend.repository.GradeRepository;
import com.example.backend.service.gradeService.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GradeServiceTest {

    private GradeRepository gradeRepository;
    private GradeService gradeService;

    @BeforeEach
    void setUp() {
        gradeRepository = Mockito.mock(GradeRepository.class);

        gradeService = new GradeService(gradeRepository);
    }

    @Test
    void testShowGradesByStudentId() {
        Long studentId = 1L;
        List<Grade> mockGrades = Arrays.asList(
                new Grade(1L, studentId, "Math", 5),
                new Grade(2L, studentId, "Physics", 4)
        );

        when(gradeRepository.findGradesByStudentId(studentId)).thenReturn(mockGrades);

        List<Grade> result = gradeService.showGradesByStudentId(studentId);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getSubject()).isEqualTo("Math");
        assertThat(result.get(1).getGrade()).isEqualTo(4);

        //how many interactions with repo, not necessary
        verify(gradeRepository, times(1)).findGradesByStudentId(studentId);
    }
}

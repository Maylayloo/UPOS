package ProffesorServiceTest;

import com.example.backend.model.Course;
import com.example.backend.model.Exam;
import com.example.backend.repository.CourseRepository;
import com.example.backend.service.professorService.ExamManagementForProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExamManagementForProfessorServiceTest {

    @InjectMocks
    private ExamManagementForProfessorService examManagementForProfessorService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScheduleExam() {
        // Given
        Long courseId = 1L;
        Course course = new Course("Anal", 5, "Dill Doe", "1", "Math");
        Exam exam = new Exam(1L, "2025-01-10", "D5-s101");

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
        Long courseId = 1L;
        Long examId = 1L;
        Course course = new Course("Math", 5, "Dr. Smith", "2", "AiR");
        Exam oldExam = new Exam(1L, "2025-01-10", "Room 101");
        course.addExam(oldExam);
        Exam updatedExam = new Exam(1L, "2025-02-15", "Room 202");

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
        Long courseId = 1L;
        Long examId = 2L; // Exam ID that doesn't exist
        Course course = new Course("Math", 5, "Dr. Smith", "3", "Mathematics");
        course.addExam(new Exam(1L, "2025-01-10", "Room 101"));
        Exam updatedExam = new Exam(2L, "2025-02-15", "Room 202");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // When
        examManagementForProfessorService.modifyExamById(courseId, examId, updatedExam);

        // Then
        assertEquals(1, course.getExams().size()); // No modification
        verify(courseRepository, never()).save(course);
    }
}

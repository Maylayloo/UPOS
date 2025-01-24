package StudentServiceTest;

import com.example.backend.model.Course;
import com.example.backend.model.Exam;
import com.example.backend.model.Student;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.ExamRepository;
import com.example.backend.service.studentService.ExamForStudentService;
import com.example.backend.service.studentService.StudentAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExamForStudentServiceTest {

    @InjectMocks
    private ExamForStudentService examForStudentService;

    @Mock
    private StudentAuthenticationService studentAuthenticationService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ExamRepository examRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllExamsByLoggedInStudent_Success() {
        // Given
        Student student = new Student();
        student.setStudentId(1L);

        List<Long> studentsId1 = new ArrayList<Long>(Arrays.asList(1L, 2L));
        Course course1 = new Course(101L, "Math", 5, 1L, "1", "Science",studentsId1);
        course1.setStudentsIds(List.of(1L, 2L));

        List<Long> studentsId2 = new ArrayList<Long>(Arrays.asList(2L, 3L));
        Course course2 = new Course(102L, "Physics", 4, 1L, "2", "Science",studentsId2);
        course2.setStudentsIds(List.of(1L, 3L));

        Exam exam1 = new Exam(1L,101L,1L, "2025-01-15", "Room 101", "1");
        exam1.setCourseId(101L);

        Exam exam2 = new Exam(2L,102L ,1L,"2025-02-20", "Room 202", "2");
        exam2.setCourseId(102L);

        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(student);
        when(courseRepository.findAll()).thenReturn(List.of(course1, course2));
        when(examRepository.findByCourseId(101L)).thenReturn(List.of(exam1));
        when(examRepository.findByCourseId(102L)).thenReturn(List.of(exam2));

        // When
        List<Exam> exams = examForStudentService.getAllExamsByLoggedInStudent();

        // Then
        assertEquals(2, exams.size());
        assertEquals("2025-01-15", exams.get(0).getDate());
        assertEquals("2025-02-20", exams.get(1).getDate());

        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(courseRepository, times(1)).findAll();
        verify(examRepository, times(1)).findByCourseId(101L);
        verify(examRepository, times(1)).findByCourseId(102L);
    }

    @Test
    void testGetAllExamsByLoggedInStudent_NoCourses() {
        // Given
        Student student = new Student();
        student.setStudentId(1L);

        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(student);
        when(courseRepository.findAll()).thenReturn(List.of());

        // When
        List<Exam> exams = examForStudentService.getAllExamsByLoggedInStudent();

        // Then
        assertEquals(0, exams.size());

        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verify(courseRepository, times(1)).findAll();
        verifyNoInteractions(examRepository);
    }

    @Test
    void testGetAllExamsByLoggedInStudent_NoStudentLoggedIn() {
        // Given
        when(studentAuthenticationService.getLoggedInStudent()).thenReturn(null);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                examForStudentService.getAllExamsByLoggedInStudent());

        assertEquals("No student is currently logged in", exception.getMessage());

        verify(studentAuthenticationService, times(1)).getLoggedInStudent();
        verifyNoInteractions(courseRepository);
        verifyNoInteractions(examRepository);
    }
}

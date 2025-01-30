package com.example.backend.service.adminService;

import com.example.backend.dto.CourseDTO;
import com.example.backend.dto.MajorGroupDTO;
import com.example.backend.mapper.CourseMapper;
import com.example.backend.mapper.GroupMapper;
import com.example.backend.model.Course;
import com.example.backend.model.MajorGroup;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.MajorGroupRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseManagementForAdminService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MajorGroupRepository majorGroupRepository;


    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    @Transactional
    public void removeCourseReferences(Long courseId) {
        List<String> tables = entityManager.createNativeQuery(
                        "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = 'course_id'")
                .getResultList();

        for (String table : tables) {
            String query = "UPDATE " + table + " SET course_id = NULL WHERE course_id = :id";
            entityManager.createNativeQuery(query)
                    .setParameter("id", courseId)
                    .executeUpdate();
        }
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course with ID " + courseId + " not found.");
        }

        removeCourseReferences(courseId);

        courseRepository.deleteById(courseId);
    }

   public void createCourse(CourseDTO courseDTO){
        Course course = new Course();
        course = CourseMapper.toModel(courseDTO);
        courseRepository.save(course);
   }

   public void createMajorGroup(MajorGroupDTO majorGroupDTO) {
        MajorGroup majorGroup = GroupMapper.toModel(majorGroupDTO);
        majorGroupRepository.save(majorGroup);
   }

    public void deleteGroup(Long id) {
       majorGroupRepository.deleteById(id);

    }
}

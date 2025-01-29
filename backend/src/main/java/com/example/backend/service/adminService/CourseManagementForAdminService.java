package com.example.backend.service.adminService;

import com.example.backend.dto.CourseDTO;
import com.example.backend.dto.MajorGroupDTO;
import com.example.backend.mapper.CourseMapper;
import com.example.backend.mapper.GroupMapper;
import com.example.backend.model.Course;
import com.example.backend.model.MajorGroup;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.MajorGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseManagementForAdminService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MajorGroupRepository majorGroupRepository;
    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
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

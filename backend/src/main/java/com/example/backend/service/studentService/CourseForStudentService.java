package com.example.backend.service.studentService;

import com.example.backend.model.Course;
import com.example.backend.model.MajorGroup;
import com.example.backend.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseForStudentService {
    public List<MajorGroup> getGroupsByLoggedInStudent(){return null;}
    public List<MajorGroup> getGroupsBySLoggedInStudentAndCourseId(Long id){return null;}
    public List<Course> getCoursesBySLoggedInStudent(){return null;}
    public void addToWF(){}
}

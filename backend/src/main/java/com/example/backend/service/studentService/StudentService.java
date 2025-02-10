package com.example.backend.service.studentService;

import com.example.backend.model.MyUser;
import com.example.backend.model.Student;
import com.example.backend.repository.MyUserRepository;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.userService.UserDataManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentAuthenticationService studentAuthenticationService;
    @Autowired
    private MyUserRepository myUserRepository;
    public Map<String,String> getNameAndSurnameByStudentId(Long studentId){
        return studentRepository.getNameAndSurnameByStudentId(studentId);
    }

    public Map<String,String> getNameAndSurnameByLoggedInStudent(){
        Student student=studentAuthenticationService.getLoggedInStudent();
        return studentRepository.getNameAndSurnameByStudentId(student.getStudentId());
    }
    public List<Map<String,String>> getNamesAndSurnamesByIds(List<Long> ids){
        List<Student> bunchOfStudends=studentRepository.findAllById(ids);
        List<Long> userids=bunchOfStudends.stream().map(Student::getUserId).toList();
        List<MyUser> bunchOfusers=myUserRepository.findAllById(userids);
        List<Map<String,String>> result= new ArrayList<>();
        for (int i=0;i<bunchOfStudends.size();i++) {
            Map<String,String> nameAndSurname=new HashMap<>();
            nameAndSurname.put("name",bunchOfusers.get(i).getName());
            nameAndSurname.put("surname",bunchOfusers.get(i).getSurname());
            nameAndSurname.put("studentId",String.valueOf( bunchOfStudends.get(i).getStudentId()));
            result.add(nameAndSurname);
        }
        return result;
    }


    public Student getStudentByStudentId(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getStudentsBymajorAndSemester(int semester,String major) {
       return studentRepository.findStudentsBySemesterAndMajor(semester,major);
    }

    public List<Map<String, String>> getStudentsNameAndSurnameByMajorAndSemester(int semester, String major) {
        List<Student> students = studentRepository.findStudentsBySemesterAndMajor(semester, major);

        return students.stream()
                .map(student -> {
                    Long studentId = student.getStudentId();
                    Map<String, String> studentInfo = studentRepository.getNameAndSurnameByStudentId(studentId);

                    // Dodajemy ID do mapy zwracanej przez repozytorium
                    studentInfo.put("studentId", studentId.toString());

                    return studentInfo;
                })
                .collect(Collectors.toList());
    }
}

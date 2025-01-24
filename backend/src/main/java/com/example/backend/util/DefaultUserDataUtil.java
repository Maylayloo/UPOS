package com.example.backend.util;

import com.example.backend.model.*;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.MajorGroupRepository;
import com.example.backend.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DefaultUserDataUtil implements CommandLineRunner {

    private final MyUserRepository myUserRepository;
    private final MajorGroupRepository majorGroupRepository;
    private final CourseRepository courseRepository;
    public DefaultUserDataUtil(MyUserRepository myUserRepository, MajorGroupRepository majorGroupRepository,CourseRepository courseRepository ) {
        this.myUserRepository = myUserRepository;
        this.majorGroupRepository = majorGroupRepository;
        this.courseRepository=courseRepository;
    }

    public static MyUser  createMyUser() {
        MyUser myUser =  new MyUser("poczta@o2.pl", PasswordUtils.encodePassword("123"), "John", "Doe", Role.PROFESSOR,"48 546 464 456","reymont56","5645764856245677546");
        return myUser;
    }
    public static MajorGroup createMajorGroup() {
        List<Long> studentIDS= Arrays.asList(1L,7L,8L,9L);
        LocalTime startTime = LocalTime.of(9,40);
        LocalTime endTime = LocalTime.of(11,45);
        MajorGroup majorGroup =  new MajorGroup(1L, "LEC", 1, DayOfTheWeek.FRIDAY,startTime,endTime, "D6-s201", 90,studentIDS);
        return majorGroup;
    }
    public static Course createCourse(){
        List<Long> studentIDS= Arrays.asList(1L,7L,8L,9L);
        Course course=new Course(3L,"Metody Numeryczne",4,1L,"1","Teleinformatics",studentIDS);
                return course;
    }
    @Override
    public void run(String... args) throws Exception {
       // MyUser myUser=createMyUser();
        //myUserRepository.save(myUser);
        //majorGroupRepository.save(createMajorGroup());



    }
}

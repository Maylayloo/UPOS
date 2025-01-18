package com.example.backend.util;

import com.example.backend.model.DayOfTheWeek;
import com.example.backend.model.MajorGroup;
import com.example.backend.model.MyUser;
import com.example.backend.model.Role;
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
    public DefaultUserDataUtil(MyUserRepository myUserRepository, MajorGroupRepository majorGroupRepository) {
        this.myUserRepository = myUserRepository;
        this.majorGroupRepository = majorGroupRepository;
    }

    public static MyUser  createMyUser() {
        MyUser myUser =  new MyUser("poczta@o2.pl", PasswordUtils.encodePassword("123"), "John", "Doe", Role.PROFESSOR,"48 546 464 456","reymont56","5645764856245677546");
        return myUser;
    }
    public static MajorGroup createMajorGroup() {
        List<Long> studentIDS= Arrays.asList(1L);
        LocalTime startTime = LocalTime.of(9,40);
        LocalTime endTime = LocalTime.of(11,45);
        MajorGroup majorGroup =  new MajorGroup(1L, "Wykład", 1, DayOfTheWeek.NIEDZIELA,startTime,endTime, "D6-s201", 90,studentIDS);
        return majorGroup;
    }

    @Override
    public void run(String... args) throws Exception {
       // MyUser myUser=createMyUser();
        //myUserRepository.save(myUser);



    }
}

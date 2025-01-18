package com.example.backend.util;

import com.example.backend.model.DayOfTheWeek;
import com.example.backend.model.MajorGroup;
import com.example.backend.repository.MajorGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultGroupUtil {


    public static void createDefaultGroup() {
        List<Long> studentIds = Arrays.asList(1L);
        LocalTime startTime = LocalTime.of(9,40);
        LocalTime endTime = LocalTime.of(11,45);
        MajorGroup majorGroup = new MajorGroup(1L, "Wykład", 1, DayOfTheWeek.NIEDZIELA,startTime,endTime, "D6-s201", 90,studentIds);
        MajorGroup labgroup = new MajorGroup(2L, "cwiczenia", 1, DayOfTheWeek.NIEDZIELA,startTime,endTime, "D5-s20sfd1", 15,studentIds);

    }
}

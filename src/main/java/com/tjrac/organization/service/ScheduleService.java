package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule getSchedule(int scheduleId);

    List<Schedule> listScheduleByCourseId( int courseId);

    boolean saveSchedule(Schedule newSchedule);

    boolean removeSchedule(int scheduleId);

    boolean updateSchedule(Schedule thisSchedule);

    List<Schedule> listAll();
}

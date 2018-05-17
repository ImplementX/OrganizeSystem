package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.ScheduleMapper;
import com.tjrac.organization.pojo.Schedule;
import com.tjrac.organization.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;


    @Override
    public Schedule getSchedule( int scheduleId ) {
        return scheduleMapper.selectByPrimaryKey( scheduleId );
    }

    @Override
    public List< Schedule > listScheduleByCourseId( int courseId ) {
        return scheduleMapper.selectByCourseId( courseId );
    }

    @Override
    public boolean saveSchedule( Schedule newSchedule ) {
        return scheduleMapper.insert( newSchedule ) > 0 ? true : false;
    }

    @Override
    public boolean removeSchedule( int scheduleId ) {
        return scheduleMapper.deleteByPrimaryKey( scheduleId ) > 0 ? true : false;
    }

    @Override
    public boolean updateSchedule( Schedule thisSchedule ) {
        return scheduleMapper.updateByPrimaryKey( thisSchedule ) > 0 ? true : false;
    }

    @Override
    public List< Schedule > listAll() {
        return scheduleMapper.selectAll();
    }
}

package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(Schedule record);

    Schedule selectByPrimaryKey(Integer scheduleId);

    List<Schedule> selectByCourseId(Integer courseId);

    List<Schedule> selectAll();

    int updateByPrimaryKey(Schedule record);

}
package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AttendanceMapper {
    int deleteByPrimaryKey(Integer attendanceId);

    int insert(Attendance record);

    Attendance selectByPrimaryKey(Integer attendanceId);

    List<Attendance> selectByStudentId(Integer studentId);

    List<Attendance> selectByCourseId(Integer courseId);

    List<Attendance> selectAll();

    int updateByPrimaryKey(Attendance record);
}
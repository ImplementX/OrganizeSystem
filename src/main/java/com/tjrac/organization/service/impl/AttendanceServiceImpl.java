package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.AttendanceMapper;
import com.tjrac.organization.pojo.Attendance;
import com.tjrac.organization.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    @Autowired
    AttendanceMapper attendanceMapper;

    @Override
    public boolean removeAttendance( int attendanceId ) {
        return attendanceMapper.deleteByPrimaryKey( attendanceId )>0?true:false;
    }

    @Override
    public boolean saveAttendance( Attendance newAttendance ) {
        return attendanceMapper.insert( newAttendance ) >0?true:false;
    }

    @Override
    public Attendance getAttendance( int attendanceId ) {
        return attendanceMapper.selectByPrimaryKey(  attendanceId);
    }

    @Override
    public List< Attendance > listByStudentId( Integer studentId ) {
        return attendanceMapper.selectByStudentId( studentId );
    }

    @Override
    public List< Attendance > listByCourseId( Integer courseId ) {
        return attendanceMapper.selectByCourseId( courseId );
    }

    @Override
    public List< Attendance > listAll( ) {
        return attendanceMapper.selectAll();
    }

    @Override
    public boolean updateAttendance( Attendance thisAttendance ) {
        return attendanceMapper.updateByPrimaryKey( thisAttendance )>0?true:false;
    }
}

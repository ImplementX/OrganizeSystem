package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Attendance;

import java.util.List;

public interface AttendanceService {
    boolean removeAttendance(int attendanceId);

    boolean saveAttendance(Attendance newAttendance);

    Attendance getAttendance(int attendanceId);

    List<Attendance> listByStudentId(Integer studentId);

    List<Attendance> listByCourseId(Integer courseId);

    List<Attendance> listAll();

    boolean updateAttendance(Attendance thisAttendance);

}

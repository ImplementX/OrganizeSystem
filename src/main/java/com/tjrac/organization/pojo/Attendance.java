package com.tjrac.organization.pojo;

public class Attendance {
    private Integer attendanceId;

    private Integer studentId;

    private Integer courseId;

    private Integer scheduleId;

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getScheduleId( ) {
        return scheduleId;
    }

    public void setScheduleId( Integer scheduleId ) {
        this.scheduleId = scheduleId;
    }
}
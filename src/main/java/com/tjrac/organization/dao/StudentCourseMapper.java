package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.StudentCourse;
import java.util.List;

public interface StudentCourseMapper {
    int deleteByPrimaryKey(Integer studentCourseId);

    int insert(StudentCourse record);

    StudentCourse selectByPrimaryKey(Integer studentCourseId);

    StudentCourse selectByStudentId(Integer studentId);

    StudentCourse selectByCourseId(Integer courseId);

    List<StudentCourse> selectAll();

    int updateByPrimaryKey(StudentCourse record);
}
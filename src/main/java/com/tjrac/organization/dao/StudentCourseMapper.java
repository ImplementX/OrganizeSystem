package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.StudentCourse;
import java.util.List;

public interface StudentCourseMapper {
    int deleteByPrimaryKey(Integer studentCourseId);

    int insert(StudentCourse record);

    StudentCourse selectByPrimaryKey(Integer studentCourseId);

    List<StudentCourse> selectByStudentId(Integer studentId);

    List<StudentCourse> selectByCourseId(Integer courseId);

    List<StudentCourse> selectAll();

    int updateByPrimaryKey(StudentCourse record);
}
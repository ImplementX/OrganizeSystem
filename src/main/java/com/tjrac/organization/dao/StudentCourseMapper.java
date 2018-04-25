package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.StudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface StudentCourseMapper {
    int deleteByPrimaryKey(Integer studentCourseId);

    int insert(StudentCourse record);

    StudentCourse selectByPrimaryKey(Integer studentCourseId);

    List<StudentCourse> selectByStudentId(Integer studentId);

    List<StudentCourse> selectByCourseId(Integer courseId);

    List<StudentCourse> selectAll();

    int updateByPrimaryKey(StudentCourse record);
}
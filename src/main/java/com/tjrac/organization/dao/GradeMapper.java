package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface GradeMapper {
    int deleteByPrimaryKey(Integer gradeId);

    int insert(Grade record);

    Grade selectByPrimaryKey(Integer gradeId);

    Grade selectByCourseIdAndStudentId( @Param( "courseId" ) Integer courseId, @Param( "studentId" ) Integer studentId);

    List<Grade> selectByCourseId(Integer courseId);

    List<Grade> selectByStudentId(Integer studentId);

    List<Grade> selectAll();

    int updateByPrimaryKey(Grade record);
}
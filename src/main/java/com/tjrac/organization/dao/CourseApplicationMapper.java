package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.CourseApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CourseApplicationMapper {
    int deleteByPrimaryKey(Integer courseApplicationId);

    int insert(CourseApplication record);

    CourseApplication selectByPrimaryKey(Integer courseApplicationId);

    List<CourseApplication> selectAll();

    List<CourseApplication> selectByStudentId(Integer studentId);

    List<CourseApplication> selectByCourseId(Integer courseId);


    int updateByPrimaryKey(CourseApplication record);
}
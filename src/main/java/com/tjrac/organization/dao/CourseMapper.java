package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Integer courseId);

    int insert(Course record);

    Course selectByPrimaryKey(Integer courseId);

    List<Course> selectByOrganizationId(Integer organizationId);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);
}
package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer teacherId);

    Teacher selectByUserId(Integer userId);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);
}
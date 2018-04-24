package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.StudentClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface StudentClassMapper {
    int deleteByPrimaryKey(Integer studentClassId);

    int insert(StudentClass record);

    StudentClass selectByPrimaryKey(Integer studentClassId);

    List<StudentClass> selectAll();

    int updateByPrimaryKey(StudentClass record);
}
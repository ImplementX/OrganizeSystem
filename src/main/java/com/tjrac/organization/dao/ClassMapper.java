package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Class;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ClassMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(Class record);

    Class selectByPrimaryKey(Integer classId);

    List<Class> selectAll();

    int updateByPrimaryKey(Class record);
}
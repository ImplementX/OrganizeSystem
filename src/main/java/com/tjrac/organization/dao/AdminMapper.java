package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    Admin selectByUserId(Integer userId);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);


}
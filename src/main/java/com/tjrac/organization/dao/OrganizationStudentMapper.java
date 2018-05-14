package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.OrganizationStudent;
import java.util.List;

public interface OrganizationStudentMapper {
    int deleteByPrimaryKey(Integer organizationStudentId);

    int insert(OrganizationStudent record);

    OrganizationStudent selectByPrimaryKey(Integer organizationStudentId);

    List<OrganizationStudent> selectAll();

    int updateByPrimaryKey(OrganizationStudent record);
}
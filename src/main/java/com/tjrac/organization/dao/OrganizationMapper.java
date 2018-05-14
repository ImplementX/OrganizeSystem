package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Organization;
import java.util.List;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer organizationId);

    int insert(Organization record);

    Organization selectByPrimaryKey(Integer organizationId);

    List<Organization> selectAll();

    int updateByPrimaryKey(Organization record);
}
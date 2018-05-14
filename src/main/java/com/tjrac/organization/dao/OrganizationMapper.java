package com.tjrac.organization.dao;

import com.tjrac.organization.pojo.Organization;
import com.tjrac.organization.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer organizationId);

    int insert(Organization record);

    Organization selectByPrimaryKey(Integer organizationId);

    List<Organization> selectAll();

    List<Organization> selectByTag(Tag tag);

    int updateByPrimaryKey(Organization record);
}
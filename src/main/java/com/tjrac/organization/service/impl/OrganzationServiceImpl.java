package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.OrganizationMapper;
import com.tjrac.organization.pojo.Organization;
import com.tjrac.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganzationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationMapper organizationMapper;

    public Organization getOrganization(int organizationId) {
        return organizationMapper.selectByPrimaryKey(organizationId);
    }

    public List<Organization> listOrganizationByTags(String tags) {
        return organizationMapper.selectByTags(tags);
    }

    public boolean saveOrganization(Organization newOrganization) {
        return organizationMapper.insert(newOrganization)>0?true:false;
    }

    public boolean removeOrganization(int organizationId) {
        return organizationMapper.deleteByPrimaryKey(organizationId)>0?true:false;
    }

    public boolean updateOrganization(Organization thisOrganization) {
        return organizationMapper.updateByPrimaryKey(thisOrganization)>0?true:false;
    }

    public List<Organization> listAll() {
        return organizationMapper.selectAll();
    }
}

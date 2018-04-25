package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Organization;

import java.util.List;

public interface OrganizationService {
    Organization getOrganization(int organizationId);

   List<Organization> listOrganizationByTags(String tags);

   boolean saveOrganization(Organization newOrganization);

   boolean removeOrganization(int organizationId);

   boolean updateOrganization(Organization thisOrganization);

   List<Organization> listAll();
}

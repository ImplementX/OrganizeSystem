package com.tjrac.organization.pojo;

public class Organization {
    private Integer organizationId;

    private String organizationName;

    private String organizationDescription;

    private Integer organizationTags;

    private Integer organizationOwner;

    private Integer organizationViceOwner;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription == null ? null : organizationDescription.trim();
    }

    public Integer getOrganizationTags() {
        return organizationTags;
    }

    public void setOrganizationTags(Integer organizationTags) {
        this.organizationTags = organizationTags;
    }

    public Integer getOrganizationOwner() {
        return organizationOwner;
    }

    public void setOrganizationOwner(Integer organizationOwner) {
        this.organizationOwner = organizationOwner;
    }

    public Integer getOrganizationViceOwner() {
        return organizationViceOwner;
    }

    public void setOrganizationViceOwner(Integer organizationViceOwner) {
        this.organizationViceOwner = organizationViceOwner;
    }
}
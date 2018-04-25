package com.tjrac.organization.pojo;

public class Organization {
    private Integer organizationId;

    private String organizationName;

    private String organizationDescription;

    private String organizationTags;

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

    public String getOrganizationTags() {
        return organizationTags;
    }

    public void setOrganizationTags(String organizationTags) {
        this.organizationTags = organizationTags;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationId=" + organizationId +
                ", organizationName='" + organizationName + '\'' +
                ", organizationDescription='" + organizationDescription + '\'' +
                ", organizationTags='" + organizationTags + '\'' +
                '}';
    }
}
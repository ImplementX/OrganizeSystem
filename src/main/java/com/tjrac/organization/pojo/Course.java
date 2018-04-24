package com.tjrac.organization.pojo;

public class Course {
    private Integer courseId;

    private String courseDescription;

    private Integer courseTeacher;

    private String courseType;

    private Integer courseOrganizationId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription == null ? null : courseDescription.trim();
    }

    public Integer getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(Integer courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType == null ? null : courseType.trim();
    }

    public Integer getCourseOrganizationId() {
        return courseOrganizationId;
    }

    public void setCourseOrganizationId(Integer courseOrganizationId) {
        this.courseOrganizationId = courseOrganizationId;
    }
}
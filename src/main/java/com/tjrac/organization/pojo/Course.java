package com.tjrac.organization.pojo;

public class Course {
    private Integer courseId;

    private String courseDescription;

    private Integer teacherId;

    private String courseType;

    private Integer organizationId;

    private Integer courseTag;

    private Integer courseIsFull;

    private Integer courseNumberLimit;

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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType == null ? null : courseType.trim();
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getCourseTag() {
        return courseTag;
    }

    public void setCourseTag(Integer courseTag) {
        this.courseTag = courseTag;
    }

    public Integer getCourseIsFull() {
        return courseIsFull;
    }

    public void setCourseIsFull(Integer courseIsFull) {
        this.courseIsFull = courseIsFull;
    }

    public Integer getCourseNumberLimit() {
        return courseNumberLimit;
    }

    public void setCourseNumberLimit(Integer courseNumberLimit) {
        this.courseNumberLimit = courseNumberLimit;
    }
}
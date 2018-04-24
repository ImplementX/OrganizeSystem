package com.tjrac.organization.pojo;

public class Class {
    private Integer classId;

    private String className;

    private Integer classCourseId;

    private Integer classMax;

    private String classTime;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getClassCourseId() {
        return classCourseId;
    }

    public void setClassCourseId(Integer classCourseId) {
        this.classCourseId = classCourseId;
    }

    public Integer getClassMax() {
        return classMax;
    }

    public void setClassMax(Integer classMax) {
        this.classMax = classMax;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime == null ? null : classTime.trim();
    }
}
package com.tjrac.organization.pojo;

public class Class {
    private Integer classId;

    private String className;

    private Integer courseId;

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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", courseId=" + courseId +
                ", classMax=" + classMax +
                ", classTime='" + classTime + '\'' +
                '}';
    }
}
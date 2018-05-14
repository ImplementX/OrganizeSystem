package com.tjrac.organization.pojo;

public class CourseApplication {
    private Integer courseApplicationId;

    private Integer courseId;

    private Integer studentId;

    private Integer courseApplicationResult;

    private Integer courseApplicationType;

    public Integer getCourseApplicationId() {
        return courseApplicationId;
    }

    public void setCourseApplicationId(Integer courseApplicationId) {
        this.courseApplicationId = courseApplicationId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseApplicationResult() {
        return courseApplicationResult;
    }

    public void setCourseApplicationResult(Integer courseApplicationResult) {
        this.courseApplicationResult = courseApplicationResult;
    }

    public Integer getCourseApplicationType() {
        return courseApplicationType;
    }

    public void setCourseApplicationType(Integer courseApplicationType) {
        this.courseApplicationType = courseApplicationType;
    }
}
package com.tjrac.organization.pojo;

public class Teacher {
    private Integer teacherId;

    private Integer teacherNumber;

    private String teacherName;

    private String teacherGender;

    private String teacherAge;

    private String teacherTel;

    private String teacherQq;

    private String teacherWechat;

    private Integer userId;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(Integer teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(String teacherGender) {
        this.teacherGender = teacherGender == null ? null : teacherGender.trim();
    }

    public String getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(String teacherAge) {
        this.teacherAge = teacherAge == null ? null : teacherAge.trim();
    }

    public String getTeacherTel() {
        return teacherTel;
    }

    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel == null ? null : teacherTel.trim();
    }

    public String getTeacherQq() {
        return teacherQq;
    }

    public void setTeacherQq(String teacherQq) {
        this.teacherQq = teacherQq == null ? null : teacherQq.trim();
    }

    public String getTeacherWechat() {
        return teacherWechat;
    }

    public void setTeacherWechat(String teacherWechat) {
        this.teacherWechat = teacherWechat == null ? null : teacherWechat.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherNumber=" + teacherNumber +
                ", teacherName='" + teacherName + '\'' +
                ", teacherGender='" + teacherGender + '\'' +
                ", teacherAge='" + teacherAge + '\'' +
                ", teacherTel='" + teacherTel + '\'' +
                ", teacherQq='" + teacherQq + '\'' +
                ", teacherWechat='" + teacherWechat + '\'' +
                ", userId=" + userId +
                '}';
    }
}
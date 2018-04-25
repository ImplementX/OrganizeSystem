package com.tjrac.organization.pojo;

public class Student {
    private Integer studentId;

    private Integer studentNumber;

    private String studentName;

    private String studentGender;

    private String studentTel;

    private String studentAge;

    private String studentQq;

    private String studentWechat;

    private String studentMajor;

    private String studentDepartment;

    private Integer userId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender == null ? null : studentGender.trim();
    }

    public String getStudentTel() {
        return studentTel;
    }

    public void setStudentTel(String studentTel) {
        this.studentTel = studentTel == null ? null : studentTel.trim();
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge == null ? null : studentAge.trim();
    }

    public String getStudentQq() {
        return studentQq;
    }

    public void setStudentQq(String studentQq) {
        this.studentQq = studentQq == null ? null : studentQq.trim();
    }

    public String getStudentWechat() {
        return studentWechat;
    }

    public void setStudentWechat(String studentWechat) {
        this.studentWechat = studentWechat == null ? null : studentWechat.trim();
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor == null ? null : studentMajor.trim();
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment == null ? null : studentDepartment.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNumber=" + studentNumber +
                ", studentName='" + studentName + '\'' +
                ", studentGender='" + studentGender + '\'' +
                ", studentTel='" + studentTel + '\'' +
                ", studentAge='" + studentAge + '\'' +
                ", studentQq='" + studentQq + '\'' +
                ", studentWechat='" + studentWechat + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", studentDepartment='" + studentDepartment + '\'' +
                ", userId=" + userId +
                '}';
    }
}
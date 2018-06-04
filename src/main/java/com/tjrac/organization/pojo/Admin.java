package com.tjrac.organization.pojo;

public class Admin {
    private Integer adminId;

    private String adminName;

    private String adminGender;

    private String adminTel;

    private String adminAge;

    private String adminMail;

    private String adminWechat;

    private String adminQq;

    private Integer userId;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminGender() {
        return adminGender;
    }

    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender == null ? null : adminGender.trim();
    }

    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel == null ? null : adminTel.trim();
    }

    public String getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(String adminAge) {
        this.adminAge = adminAge == null ? null : adminAge.trim();
    }

    public String getAdminMail( ) {
        return adminMail;
    }

    public void setAdminMail( String adminMail ) {
        this.adminMail = adminMail;
    }

    public String getAdminWechat() {
        return adminWechat;
    }

    public void setAdminWechat(String adminWechat) {
        this.adminWechat = adminWechat == null ? null : adminWechat.trim();
    }

    public String getAdminQq() {
        return adminQq;
    }

    public void setAdminQq(String adminQq) {
        this.adminQq = adminQq == null ? null : adminQq.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString( ) {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminGender='" + adminGender + '\'' +
                ", adminTel='" + adminTel + '\'' +
                ", adminAge='" + adminAge + '\'' +
                ", adminMail='" + adminMail + '\'' +
                ", adminWechat='" + adminWechat + '\'' +
                ", adminQq='" + adminQq + '\'' +
                ", userId=" + userId +
                '}';
    }
}
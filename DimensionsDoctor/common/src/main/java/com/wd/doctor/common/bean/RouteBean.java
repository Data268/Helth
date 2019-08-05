package com.wd.doctor.common.bean;

public class RouteBean {
    private String email;
    private String code;
    private String pwd1;
    private String pwd2;
    private String name;
    private String inauguralHospital;
    private String departmentName;
    private String jobTitle;
    private String personalProfile;
    private String goodField;

    public RouteBean(String email, String code, String pwd1, String pwd2, String name, String inauguralHospital, String departmentName, String jobTitle, String personalProfile, String goodField) {
        this.email = email;
        this.code = code;
        this.pwd1 = pwd1;
        this.pwd2 = pwd2;
        this.name = name;
        this.inauguralHospital = inauguralHospital;
        this.departmentName = departmentName;
        this.jobTitle = jobTitle;
        this.personalProfile = personalProfile;
        this.goodField = goodField;
    }

    @Override
    public String toString() {
        return "RouteBean{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", pwd1='" + pwd1 + '\'' +
                ", pwd2='" + pwd2 + '\'' +
                ", name='" + name + '\'' +
                ", inauguralHospital='" + inauguralHospital + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", personalProfile='" + personalProfile + '\'' +
                ", goodField='" + goodField + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPwd1() {
        return pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInauguralHospital() {
        return inauguralHospital;
    }

    public void setInauguralHospital(String inauguralHospital) {
        this.inauguralHospital = inauguralHospital;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getGoodField() {
        return goodField;
    }

    public void setGoodField(String goodField) {
        this.goodField = goodField;
    }
}

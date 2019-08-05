package com.wd.doctor.common.bean;

public class FindDoctorBean {
    private int id;
    private String name;
    private String inauguralHospital;
    private String jobTitle;
    private String imagePic;
    private String departmentId;
    private String departmentName;
    private String personalProfile;
    private String goodField;

    public FindDoctorBean(int id, String name, String inauguralHospital, String jobTitle, String imagePic, String departmentId, String departmentName, String personalProfile, String goodField) {
        this.id = id;
        this.name = name;
        this.inauguralHospital = inauguralHospital;
        this.jobTitle = jobTitle;
        this.imagePic = imagePic;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.personalProfile = personalProfile;
        this.goodField = goodField;
    }

    @Override
    public String toString() {
        return "FindDoctorBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inauguralHospital='" + inauguralHospital + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", imagePic='" + imagePic + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", personalProfile='" + personalProfile + '\'' +
                ", goodField='" + goodField + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getImagePic() {
        return imagePic;
    }

    public void setImagePic(String imagePic) {
        this.imagePic = imagePic;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

package com.wd.doctor.common.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class LoginBean {

    private Long id;

    private int departmentId;
    private String departmentName;
    private String inauguralHospital;
    private String jiGuangPwd;
    private String jobTitle;
    private String name;
    private int reviewStatus;
    private String sessionId;
    private String userName;
    private String imagePic;
    private int whetherHaveImagePic;

    int status;//记录本地用户登录状态，用于直接登录和退出,1:登录，0：未登录或退出

    @Generated(hash = 656023496)
    public LoginBean(Long id, int departmentId, String departmentName,
            String inauguralHospital, String jiGuangPwd, String jobTitle,
            String name, int reviewStatus, String sessionId, String userName,
            String imagePic, int whetherHaveImagePic, int status) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.inauguralHospital = inauguralHospital;
        this.jiGuangPwd = jiGuangPwd;
        this.jobTitle = jobTitle;
        this.name = name;
        this.reviewStatus = reviewStatus;
        this.sessionId = sessionId;
        this.userName = userName;
        this.imagePic = imagePic;
        this.whetherHaveImagePic = whetherHaveImagePic;
        this.status = status;
    }

    @Generated(hash = 1112702939)
    public LoginBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getInauguralHospital() {
        return this.inauguralHospital;
    }

    public void setInauguralHospital(String inauguralHospital) {
        this.inauguralHospital = inauguralHospital;
    }

    public String getJiGuangPwd() {
        return this.jiGuangPwd;
    }

    public void setJiGuangPwd(String jiGuangPwd) {
        this.jiGuangPwd = jiGuangPwd;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReviewStatus() {
        return this.reviewStatus;
    }

    public void setReviewStatus(int reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImagePic() {
        return this.imagePic;
    }

    public void setImagePic(String imagePic) {
        this.imagePic = imagePic;
    }

    public int getWhetherHaveImagePic() {
        return this.whetherHaveImagePic;
    }

    public void setWhetherHaveImagePic(int whetherHaveImagePic) {
        this.whetherHaveImagePic = whetherHaveImagePic;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



}

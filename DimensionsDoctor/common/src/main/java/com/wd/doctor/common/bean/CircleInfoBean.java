package com.wd.doctor.common.bean;

public class CircleInfoBean {

    /**
     * amount : 0
     * authorName : Tu_EHSDN
     * departmentId : 2
     * departmentName : 骨科
     * detail : 详情
     * disease : 病症描述
     * id : 4
     * picture : http://172.17.8.100/images/health/user/head_pic/default/default_head_1.jpg
     * title : 急寻：面神经炎的治疗方法
     * treatmentEndTime : 1542902400000
     * treatmentHospital : 北京医院
     * treatmentProcess : 化疗
     * treatmentStartTime : 1524412800000
     * userId : 2
     * whetherContent : 2
     */

    private int amount;
    private String authorName;
    private int departmentId;
    private String departmentName;
    private String detail;
    private String disease;
    private int id;
    private String picture;
    private String title;
    private long treatmentEndTime;
    private String treatmentHospital;
    private String treatmentProcess;
    private long treatmentStartTime;
    private int userId;
    private int whetherContent;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTreatmentEndTime() {
        return treatmentEndTime;
    }

    public void setTreatmentEndTime(long treatmentEndTime) {
        this.treatmentEndTime = treatmentEndTime;
    }

    public String getTreatmentHospital() {
        return treatmentHospital;
    }

    public void setTreatmentHospital(String treatmentHospital) {
        this.treatmentHospital = treatmentHospital;
    }

    public String getTreatmentProcess() {
        return treatmentProcess;
    }

    public void setTreatmentProcess(String treatmentProcess) {
        this.treatmentProcess = treatmentProcess;
    }

    public long getTreatmentStartTime() {
        return treatmentStartTime;
    }

    public void setTreatmentStartTime(long treatmentStartTime) {
        this.treatmentStartTime = treatmentStartTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWhetherContent() {
        return whetherContent;
    }

    public void setWhetherContent(int whetherContent) {
        this.whetherContent = whetherContent;
    }
}

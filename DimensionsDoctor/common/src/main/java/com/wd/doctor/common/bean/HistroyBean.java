package com.wd.doctor.common.bean;

public class HistroyBean {
    private int recordId;
    private int userId;
    private String userHeadPic;
    private String doctorHeadPic;
    private String nickName;
    private long inquiryTime;
    private int status;

    @Override
    public String toString() {
        return "HistroyBean{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", userHeadPic='" + userHeadPic + '\'' +
                ", doctorHeadPic='" + doctorHeadPic + '\'' +
                ", nickName='" + nickName + '\'' +
                ", inquiryTime=" + inquiryTime +
                ", status=" + status +
                '}';
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserHeadPic() {
        return userHeadPic;
    }

    public void setUserHeadPic(String userHeadPic) {
        this.userHeadPic = userHeadPic;
    }

    public String getDoctorHeadPic() {
        return doctorHeadPic;
    }

    public void setDoctorHeadPic(String doctorHeadPic) {
        this.doctorHeadPic = doctorHeadPic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getInquiryTime() {
        return inquiryTime;
    }

    public void setInquiryTime(long inquiryTime) {
        this.inquiryTime = inquiryTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HistroyBean(int recordId, int userId, String userHeadPic, String doctorHeadPic, String nickName, long inquiryTime, int status) {
        this.recordId = recordId;
        this.userId = userId;
        this.userHeadPic = userHeadPic;
        this.doctorHeadPic = doctorHeadPic;
        this.nickName = nickName;
        this.inquiryTime = inquiryTime;
        this.status = status;
    }
}

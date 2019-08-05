package com.wd.doctor.common.bean;

public class ReadNotBean {
    private int notReadNum;
    private int noticeType;

    @Override
    public String toString() {
        return "ReadNotBean{" +
                "notReadNum=" + notReadNum +
                ", noticeType=" + noticeType +
                '}';
    }

    public int getNotReadNum() {
        return notReadNum;
    }

    public void setNotReadNum(int notReadNum) {
        this.notReadNum = notReadNum;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public ReadNotBean(int notReadNum, int noticeType) {
        this.notReadNum = notReadNum;
        this.noticeType = noticeType;
    }
}

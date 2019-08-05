package com.wd.doctor.common.bean;

public class FindSickBean {

    private int sickCircleId;
    private String title;
    private String detail;
    private long releaseTime;
    private int amount;

    @Override
    public String toString() {
        return "FindSickBean{" +
                "sickCircleId=" + sickCircleId +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", releaseTime=" + releaseTime +
                ", amount=" + amount +
                '}';
    }

    public int getSickCircleId() {
        return sickCircleId;
    }

    public void setSickCircleId(int sickCircleId) {
        this.sickCircleId = sickCircleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public FindSickBean(int sickCircleId, String title, String detail, long releaseTime, int amount) {
        this.sickCircleId = sickCircleId;
        this.title = title;
        this.detail = detail;
        this.releaseTime = releaseTime;
        this.amount = amount;
    }
}

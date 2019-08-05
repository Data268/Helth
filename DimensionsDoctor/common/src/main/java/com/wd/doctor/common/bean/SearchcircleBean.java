package com.wd.doctor.common.bean;

public class SearchcircleBean {

    /**
     * amount : 10
     * detail : 疼起来像针扎一样
     * releaseTime : 1564070400000
     * sickCircleId : 594
     * title : 头疼的厉害
     */

    private int amount;
    private String detail;
    private long releaseTime;
    private int sickCircleId;
    private String title;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
}

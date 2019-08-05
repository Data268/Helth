package com.wd.doctor.common.bean;

public class InquiryNotBean {
    private int id;
    private String content;
    private long createTime;

    @Override
    public String toString() {
        return "InquiryNotBean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public InquiryNotBean(int id, String content, long createTime) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
    }
}

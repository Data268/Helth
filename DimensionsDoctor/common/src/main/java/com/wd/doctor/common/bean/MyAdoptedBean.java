package com.wd.doctor.common.bean;

public class MyAdoptedBean {
    private int releaseUserId;
    private String releaseUserNickName;
    private String releaseUserHeadPic;
    private String title;
    private String disease;
    private String content;
    private long adoptTime;

    @Override
    public String toString() {
        return "MyAdoptedBean{" +
                "releaseUserId=" + releaseUserId +
                ", releaseUserNickName='" + releaseUserNickName + '\'' +
                ", releaseUserHeadPic='" + releaseUserHeadPic + '\'' +
                ", title='" + title + '\'' +
                ", disease='" + disease + '\'' +
                ", content='" + content + '\'' +
                ", adoptTime=" + adoptTime +
                '}';
    }

    public int getReleaseUserId() {
        return releaseUserId;
    }

    public void setReleaseUserId(int releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    public String getReleaseUserNickName() {
        return releaseUserNickName;
    }

    public void setReleaseUserNickName(String releaseUserNickName) {
        this.releaseUserNickName = releaseUserNickName;
    }

    public String getReleaseUserHeadPic() {
        return releaseUserHeadPic;
    }

    public void setReleaseUserHeadPic(String releaseUserHeadPic) {
        this.releaseUserHeadPic = releaseUserHeadPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAdoptTime() {
        return adoptTime;
    }

    public void setAdoptTime(long adoptTime) {
        this.adoptTime = adoptTime;
    }

    public MyAdoptedBean(int releaseUserId, String releaseUserNickName, String releaseUserHeadPic, String title, String disease, String content, long adoptTime) {
        this.releaseUserId = releaseUserId;
        this.releaseUserNickName = releaseUserNickName;
        this.releaseUserHeadPic = releaseUserHeadPic;
        this.title = title;
        this.disease = disease;
        this.content = content;
        this.adoptTime = adoptTime;
    }
}

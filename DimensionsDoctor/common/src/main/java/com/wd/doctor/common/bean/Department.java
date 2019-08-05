package com.wd.doctor.common.bean;

public class Department {

    /**
     * createTime : 1547021902000
     * departmentName : 内科
     * id : 7
     * pic : http://172.17.8.100/images/health/department_pic/nk.jpg
     * rank : 1
     */

    private long createTime;
    private String departmentName;
    private int id;
    private String pic;
    private int rank;
    int textcolor = 0xff333333;

    public int getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(int textcolor) {
        this.textcolor = textcolor;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

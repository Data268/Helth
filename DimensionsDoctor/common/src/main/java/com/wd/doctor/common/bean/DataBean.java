package com.wd.doctor.common.bean;

/**
 * description ：
 * project name：DimensionMedical
 * author : 李旭斌
 * creation date: 2019/7/10 16:23
 *
 * @version 1.0
 */
public class DataBean<T> {
    private String message;
    private String status;
    private  T result;

    public DataBean(String message, String status, T result) {
        this.message = message;
        this.status = status;
        this.result = result;
    }


    @Override
    public String toString() {
        return "DataBean{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

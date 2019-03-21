package com.api.httpUtil.精简.httpUtil.postMain.test;

public class ApiRetureDTO {

    private String time; // 时间 2018-06-14
    private int maxQPS;// 最高并发量
    private long dosage;// 用量
    private long exceededRequest; // 超限额请求次数
    private String successTate; // 成功率


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMaxQPS() {
        return maxQPS;
    }

    public void setMaxQPS(int maxQPS) {
        this.maxQPS = maxQPS;
    }

    public long getDosage() {
        return dosage;
    }

    public void setDosage(long dosage) {
        this.dosage = dosage;
    }

    public long getExceededRequest() {
        return exceededRequest;
    }

    public void setExceededRequest(long exceededRequest) {
        this.exceededRequest = exceededRequest;
    }

    public String getSuccessTate() {
        return successTate;
    }

    public void setSuccessTate(String successTate) {
        this.successTate = successTate;
    }
}

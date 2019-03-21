package com.api.httpUtil.精简.httpUtil.postMain.test;

public class GatewayStatisticsDTO {

    private String ip;

    private int port;

    private String url;

    private long appId;

    private long currentTime;

    private int status;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "GateWayStatisticsDTO{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", url='" + url + '\'' +
                ", appId=" + appId +
                ", currentTime=" + currentTime +
                ", status=" + status +
                '}';
    }
}

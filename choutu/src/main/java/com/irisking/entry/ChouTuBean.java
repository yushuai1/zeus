package com.irisking.entry;

public class ChouTuBean {

    private String picInfo;

    private String zhuce;

    private String shibie;

    private boolean flag;

    private String info;

    public ChouTuBean() {
    }

    public ChouTuBean(String picInfo, String zhuce, String shibie, boolean flag) {
        this.picInfo = picInfo;
        this.zhuce = zhuce;
        this.shibie = shibie;
        this.flag = flag;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(String picInfo) {
        this.picInfo = picInfo;
    }

    public String getZhuce() {
        return zhuce;
    }

    public void setZhuce(String zhuce) {
        this.zhuce = zhuce;
    }

    public String getShibie() {
        return shibie;
    }

    public void setShibie(String shibie) {
        this.shibie = shibie;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


}

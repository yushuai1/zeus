package com.api.postgresql.entry;

public class IrisTest {

    private int id;
    private String feature;

    private int appid;

    private int flag;

    public IrisTest(int id, String feature, int appid, int flag) {
        this.id = id;
        this.feature = feature;
        this.appid = appid;
        this.flag = flag;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IrisTest{" +
                "feature='" + feature + '\'' +
                ", appid=" + appid +
                ", flag=" + flag +
                '}';
    }
}

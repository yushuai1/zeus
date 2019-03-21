package com.分布式.缓存穿透.本地缓存实现.testSql;

public class TestUser {

    private String d;
    private int m;

    public TestUser(String d, int m) {
        this.d = d;
        this.m = m;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "d='" + d + '\'' +
                ", m=" + m +
                '}';
    }
}

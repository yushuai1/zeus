package com.正则;

import java.util.UUID;

public class UUIDS {

    private String uuid = UUID.randomUUID().toString().replace("-","");

    private int m = 0;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
}

package com.api.postgresql.entry;

public class Testyu {

    private int id;

    private String name;

    private byte[] feature;

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFeature() {
        return feature;
    }

    public void setFeature(byte[] feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Testyu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", feature=" + feature +
                '}';
    }
}

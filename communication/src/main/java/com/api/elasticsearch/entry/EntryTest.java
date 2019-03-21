package com.api.elasticsearch.entry;

public class EntryTest {

    private String name;

    private String idString;

    private int countNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public int getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(int countNumber) {
        this.countNumber = countNumber;
    }

    @Override
    public String toString() {
        return "EntryTest{" +
                "name='" + name + '\'' +
                ", idString='" + idString + '\'' +
                ", countNumber=" + countNumber +
                '}';
    }
}

package com.service;

public class MemoryCount implements Comparable<MemoryCount> {

    /**
     * 分片名
     */
    String key;
    /**
     * appID号
     */
    int values;

    public MemoryCount() {
    }

    public MemoryCount(String key, int values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    @Override
    public int compareTo(MemoryCount o) {
        MemoryCount pview = (MemoryCount)o;
        int count1 = pview.getValues();
        int count2 = this.hashCode();
        return count2>count1 ? 1 :-1;
    }
}

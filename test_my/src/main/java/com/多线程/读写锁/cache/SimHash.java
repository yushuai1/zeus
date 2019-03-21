package com.多线程.读写锁.cache;

public class SimHash {
    private int cap;
    private int seed;

    public SimHash(int cap,int seed) {
        this .cap  =  cap;
        this .seed  =  seed;
    }

    public int hash(String value) {
        int result = 0 ;
        int len = value.length();
        for (int i = 0 ; i < len; i ++ ) {
            int at = value.charAt(i);
            result = seed * result + at;
        }
        int x = (cap - 1 ) & result;
        return x;
    }
}

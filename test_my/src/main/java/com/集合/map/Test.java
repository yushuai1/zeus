package com.集合.map;

import java.util.HashMap;
import java.util.Map;


public class Test {
    public static final int   MAX_VALUE = 0x7fffffff;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAX_VALUE) ? MAX_VALUE : n + 1;
    }

    public static void main(String[] as){
        Map<String,String> map = new HashMap<>();
        map.put("sa","as");
        System.out.println((256*512*512));
        System.out.println(tableSizeFor(256*512*512));
    }
}

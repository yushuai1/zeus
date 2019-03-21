package com.service;


import java.util.*;

public class Test {


    public static void main(String[] sd) {


        HashMap<String, Integer> map = new HashMap<String, Integer>();

        System.out.println("unsorted map: " + map);

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (int) (o2.getValue() - o1.getValue());
            }
        });

        System.out.println("results: " + list);

    }
}

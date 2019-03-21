package com.lambda.Stream;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionStream {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long t2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int l = 0;
            for (String s : strings) {
                if (s.isEmpty()){
                    l++;
                }
            }
        }
        System.out.println(System.currentTimeMillis() - t2);
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        }
        System.out.println(System.currentTimeMillis() - t1);

        /******************forEach********************************************/
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        items.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));
        long t3 = System.currentTimeMillis();
        for (int m = 0; m < 10000; m++) {
            /**
             * 比普通的遍历更快
             */
            items.forEach((k, v) -> {
//                System.out.println("Item : " + k + " Count : " + v);
                if ("E".equals(k)) {
//                    System.out.println("Hello E");
                }
            });
        }
        System.out.println(System.currentTimeMillis() - t3);
        long t4 = System.currentTimeMillis();
        for (int m = 0; m < 10000; m++) {
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
//                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if ("E".equals(entry.getKey())) {

                }
            }
        }
        System.out.println(System.currentTimeMillis() - t3);

        /************************************filter**********************************************/
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long t5 = System.currentTimeMillis();
        for (int k = 0; k < 10000; k++) {
            int count = (int) list.stream().filter(string -> string.isEmpty()).count();

        }
        System.out.println(System.currentTimeMillis() - t5);
        /***************************************排序***********************************************/
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }
}

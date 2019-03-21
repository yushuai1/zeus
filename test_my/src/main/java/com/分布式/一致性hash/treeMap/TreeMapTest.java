package com.分布式.一致性hash.treeMap;

import org.springframework.data.domain.Sort;
import java.util.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class TreeMapTest {

    public static void main(String[] args) {
        sortMap();
//        treeMapValus排序();
//        TreeMapkey降序();
    }

    private static void sortMap() {
        SortedMap<String,String> map = null ;
        map = new TreeMap<String,String>() ;   //通过子类实例化接口对象
        map.put("D","DDDDD") ;
        map.put("A","AAAAA") ;
        map.put("C","CCCCC") ;
        map.put("B","BBBBB") ;
        System.out.println("第一个元素的key:" + map.firstKey()) ;
        System.out.println("key对于的值为:" + map.get(map.firstKey())) ;
        System.out.println("最后一个元素的key:" + map.lastKey()) ;
        System.out.println("key对于的值为:" + map.get(map.lastKey())) ;
        System.out.println("返回小于指定范围的集合（键值小于“A”）") ;
        for(Entry<String,String> me:map.headMap("A").entrySet()){
            System.out.println("\t|- " + me.getKey() + "-->" + me.getValue()) ;
        }
        System.out.println("返回大于指定范围的集合（键值大于等于“F”）") ;
        for(Entry<String,String> me:map.tailMap("F").entrySet()){
            System.out.println("\t|- " + me.getKey() + "-->" + me.getValue()) ;
        }
        System.out.println("返回部分集合（键值“B”和“D”之间,包括B不包括D）") ;
        for(Entry<String,String> me:map.subMap("B","D").entrySet()){
            System.out.println("\t|- " + me.getKey() + "-->" + me.getValue()) ;
        }
    }

    private static void treeMapValus排序() {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("a", "ddddd");
        map.put("c", "bbbbb");
        map.put("d", "aaaaa");
        map.put("b", "ccccc");
        //这里将map.entrySet()转换成list
        List<Map.Entry<String,String>> entryList = new ArrayList<>(map.entrySet());
        ////然后通过比较器来实现排序
        Collections.sort(entryList, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for(Map.Entry<String,String> mapping:entryList){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
    }

    /**
     * treeMap默认是按照key升序排序的，当然也可以用接口实现降序排序
     * 普通的hashmap是没有compare接口的
     */
    private static void TreeMapkey降序() {
        Map<Double, String> map = new TreeMap<Double, String>(
                new Comparator<Double>() {
                    @Override
                    public int compare(Double obj1, Double obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                }
                );
        map.put(2.33, "ccccc");
        map.put(2.0, "aaaaa");
        map.put(3.0, "bbbbb");
        map.put(4.0, "ddddd");

        Set<Double> keySet = map.keySet();
        Iterator<Double> iter = keySet.iterator();
        while (iter.hasNext()) {
            Double key = iter.next();
            System.out.println(key + ":" + map.get(key));
        }
    }

}

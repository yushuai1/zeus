package com.彩票;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UtilTool {

    public static ExecutorService executor = new ThreadPoolExecutor(4, 4,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(3));

    public static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(1000);

    public static Map<Integer,Integer> mapTest = new HashMap<Integer,Integer>();

    public static Map<Integer,Integer> mapResult = new HashMap<Integer,Integer>();

    public static int getHash(String str) {

        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0) {
            hash = Math.abs(hash);
        }

        return hash;
    }


    /**
     * 使用 Map按value进行排序
     * @return
     */
    public static Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<Integer, Integer>> iter = entryList.iterator();
        Map.Entry<Integer, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    static class MapValueComparator implements Comparator<Map.Entry<Integer, Integer>> {

        @Override
        public int compare(Map.Entry<Integer, Integer> me1, Map.Entry<Integer, Integer> me2) {

            return me1.getValue().compareTo(me2.getValue());
        }
    }
}

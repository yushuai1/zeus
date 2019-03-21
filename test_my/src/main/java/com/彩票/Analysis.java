package com.彩票;

import java.util.*;

import static com.彩票.UtilTool.getHash;
import static com.彩票.UtilTool.mapTest;
import static com.彩票.UtilTool.sortMapByValue;

public class Analysis {

    //多少个比对一次
    public static int number1 = 4;

    //预测多少个数字
    public static int number0 = 3;

    public static Map<Integer, Integer> map3 = new HashMap<>();

    public static void main(String[] asd) throws Exception {

        Analysis analysis = new Analysis();
        List<String> list = analysis.getNumber();

        int mk = list.size();

        ERS:
        for (int m = 0; m < mk - 4; m++) {
            List<String> list1 = list.subList(m, m + number1 + 1);

            int km = list1.size();
            String 最后一组数字 = list1.get(km - 1);
            String[] 最后一组数组 = 最后一组数字.split("-");

            Map<Integer, Integer> map = new HashMap<>();
            for (int mi = 1; mi < 12; mi++) {
                map.put(mi, 0);
            }

            for (int mn = 0; mn < km - 1; mn++) {

                String 号码 = list1.get(mn);
                String[] 号码数组 = 号码.split("-");
                for (String sd : 号码数组) {
                    int vb = Integer.parseInt(sd);
                    map.put(vb, map.get(vb) + 1);
                }
            }

            Map<Integer, Integer> map1 = sortMapByValue(map);


            int mkls = 0;
            int 选出号码个数 = 0;
            ER:
            for (Map.Entry<Integer, Integer> csdn : map1.entrySet()) {

                SD:
                for (String in : 最后一组数组) {
                    int iks = Integer.parseInt(in);
                    if (iks == csdn.getKey()) {
                        mkls++;
                        break SD;
                    }
                }
                选出号码个数++;
                if (选出号码个数==2){
                    if (csdn.getValue()!=0){
                        break ERS;
                    }
                }
                if (选出号码个数 == number0) {
                    break ER;
                }
            }

            if (map3.containsKey(mkls)) {
                map3.put(mkls,map3.get(mkls)+1);
            }else {
                map3.put(mkls,1);
            }
            System.out.println(map3.toString());
        }


    }


    public List<String> getNumber() throws Exception {
        List<String> stringList = new ArrayList<>();
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Set<Integer> set = new HashSet<>();
        while (true) {
            Thread.sleep(10L);
            String uuid = UUID.randomUUID().toString();
            int index = getHash(uuid);
            int k = index % 11 + 1;
            if (set.contains(k)) {
                continue;
            }
            if (i == 4) {
                stringBuffer.append(k);
                UtilTool.queue.put(stringBuffer.toString());
                stringList.add(stringBuffer.toString());

                if (stringList.size() == 1000) {
                    return stringList;
                }
                stringBuffer = new StringBuffer();
                set = new HashSet<>();
                i = 0;
            } else if (i < 4) {
                stringBuffer.append(k).append("-");
                set.add(k);
                i++;
            }
        }
    }
}

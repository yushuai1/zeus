package com.工作测试.图片;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Test {

    public static void main(String[] asd) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH-mm-ss");//24小时制
        Date date2 = new Date();
        date2.setTime(1520316682980L);
        System.out.println(simpleDateFormat.format(date2));


//        for (int i=0;i<100;i++)
//        getPersion();
    }

    private static void getPersion() {
        String[] 人员 = {"孙", "苑", "谢", "段"};
        System.out.println("开始选取今天谁请吃饭！！！");
        String uuid = UUID.randomUUID().toString();
        int index = getHash(uuid);
        int 下标 = index % 人员.length;
        System.out.println("结果为 -----" + 人员[下标] + "-----请吃饭！");
    }

    private static int getHash(String str) {
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
}

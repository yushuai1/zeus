package com.分布式.缓存穿透.本地缓存实现;


public class Test {

    public static void main(String[] args) {

        int m = Integer.MAX_VALUE;
        int k = (m-1) >> 6;

        int l = 65;
        int nm = l>>6;

        long[] as = new long[k + 1];
        as[0] = 1111L;
        as[1] = 222L;
        System.out.println(as.length);

//        filterTest();

    }

    private static void filterTest() {
        String value = "stone2083@yahoo.cn" ;
        SimFilter filter = new  SimFilter();

        long t1 = System.currentTimeMillis();
        for (int m = 0;m<10000000;m++){
            filter.add(value+m);
        }
        System.out.println("添加一千万时间："+(System.currentTimeMillis()-t1));
//        filter.remove(value+1235);
        long t2 = System.currentTimeMillis();
        for (int m = 0;m<10000000;m++){
            if (!filter.contains(value+m)){
                System.out.println("出现误差："+m);
            }
        }
        System.out.println("识别一千万时间："+(System.currentTimeMillis()-t2));
        System.out.println(filter.contains(value+1235));
        System.out.println(filter.contains(value+100000001));
    }
}

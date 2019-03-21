package com.分布式.缓存穿透.本地缓存实现.testSql;

import com.分布式.缓存穿透.本地缓存实现.SimCache;
import com.分布式.缓存穿透.本地缓存实现.SimDataSql;

public class Test {

    public static void main(String[] as){

        SimDataSql testSql = new TestSql();
        SimCache<Integer,TestUser> simCache = new SimCache(testSql);

        long t1 = System.currentTimeMillis();

        for (int i=0;i<100;i++){
            simCache.put(2,new TestUser("shi",12));
            System.out.println(simCache.get(2).toString());
            simCache.remove(2);
            System.out.println(simCache.getData(1).toString());
        }
        System.out.println("识别一千万时间："+(System.currentTimeMillis()-t1));
    }
}

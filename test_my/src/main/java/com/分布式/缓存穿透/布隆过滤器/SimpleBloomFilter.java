package com.分布式.缓存穿透.布隆过滤器;

import java.util.BitSet;

public  class SimpleBloomFilter {

    private static final int DEFAULT_SIZE = Integer.MAX_VALUE ;
    private static final int [] seeds = new int [] {7,11,13,31,37,61,25,56};

    private BitSet bits = new BitSet(DEFAULT_SIZE);
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public static void main(String[] args) {
        String value = "stone2083@yahoo.cn" ;
        SimpleBloomFilter filter = new  SimpleBloomFilter();

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

    public  SimpleBloomFilter() {
        for  ( int  i = 0 ; i < seeds.length; i ++ ) {
            func[i] = new  SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    public void add(String value) {
        for  (SimpleHash f : func) {
            int x= f.hash(value);
            bits.set(x, true );
        }
    }

    public void remove(String value) {
        for  (SimpleHash f : func) {
            int x= f.hash(value);
            bits.set(x, false );
        }
    }

    public boolean contains(String value) {
        if (value == null ) {
            return false ;
        }
        boolean ret = true ;
        for (SimpleHash f : func) {
            int x = f.hash(value);
            boolean fg = bits.get(x);
            ret = ret && fg;
        }
        return ret;
    }
}
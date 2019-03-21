package com.分布式.缓存穿透.本地缓存实现;

import java.util.BitSet;

public class SimFilter {

    private static final int DEFAULT_SIZE = Integer.MAX_VALUE ;
    private static final int [] seeds = new int [] {7,11,13,31,37,61,25,56};

    private BitSet bits = new BitSet(DEFAULT_SIZE);
    private SimHash[] func = new SimHash[seeds.length];



    public SimFilter() {
        for  ( int  i = 0 ; i < seeds.length; i ++ ) {
            func[i] = new SimHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    public void add(String value) {
        for  (SimHash f : func) {
            int x= f.hash(value);
            bits.set(x, true );
        }
    }

    public void remove(String value) {
        for  (SimHash f : func) {
            int x= f.hash(value);
            bits.set(x, false );
        }
    }

    public boolean contains(String value) {
        if (value == null ) {
            return false ;
        }
        boolean ret = true ;
        for (SimHash f : func) {
            int x = f.hash(value);
            boolean fg = bits.get(x);
            ret = ret && fg;
        }
        return ret;
    }
}

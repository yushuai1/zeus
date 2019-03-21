package com.移位运算;

public class Test {


    public static void main(String[] asd){


        int s = (2^0+2^1+2^2+2^3)| (2^3+2^7);

        System.out.println(Integer.toBinaryString(143));
        long t1 = System.currentTimeMillis();

        int val,b,a;
        for (val = 0; val < 100000000; val += 1)
        {
            a = val * 8;
            b = val / 2;
        }
        System.out.println("time :"+(System.currentTimeMillis()-t1));

        if ("".equals("")){

        }
    }
}

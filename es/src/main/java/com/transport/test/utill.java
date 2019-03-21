package com.transport.test;

import java.util.Random;

public class utill {

    private static String getChinese(long seed)
            throws Exception {
        String str = null;
        int highpos, lowpos;
        Random random = new Random(seed);
        highpos = (176 + Math.abs(random.nextInt(39)));
        lowpos = (161 + Math.abs(random.nextInt(93)));
        byte[] bb = new byte[2];
        bb[0] = new Integer(highpos).byteValue();
        bb[1] = new Integer(lowpos).byteValue();

        str = new String(bb, "GBK");
        return str;
    }

    public static String getString()  {
        try {
            return getChinese((long) (Math.random()*Integer.MAX_VALUE))+getChinese((long) (Math.random()*Integer.MAX_VALUE))+getChinese((long) (Math.random()*Integer.MAX_VALUE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(getString());
    }
}

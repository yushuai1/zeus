package com.工作测试.字符串效率;

public class Test {

    private static int getHash(String str)
    {
        final int p = 167;
        int hash = (int)211L;
        for (int i = 0; i < str.length(); i++){
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0){
            hash = Math.abs(hash);
        }
        return hash;
    }

    public static void main(String[] args) {

        System.out.println();

        String sd = "/api/jkjl/asbj1/nkl";

        long t1 = System.currentTimeMillis();
        for (int k=0;k<500000;k++){
            getStart(sd,"/api/jkjl/asbj"+1);
        }
        System.out.println(System.currentTimeMillis()-t1);
    }

    private static boolean getStart(String sd,String sd1) {
       return sd.startsWith(sd1);
    }
}

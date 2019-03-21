package com.工作测试.bit数组相识度.hjhjhj;

import java.util.*;

public class 四位一组 {

    private static TreeSet<Integer> tree = new TreeSet<>();
    private static Map<Integer, Integer> map = new HashMap<>(2144);

    private static byte[][] 初始化数据1() {
        byte[][] blist = new byte[19][512];

        for (int i = 0; i < 19; i++) {
            blist[i] = gBytes();
        }
        return blist;
    }

    public static int[] kk = new int[Integer.MAX_VALUE -2];
    public static List<Integer> kkl = new ArrayList<>(16);

    static {
        kk[1] = 1254635;
        kkl.add(16456);
        kkl.add(16456);
        kkl.add(16456);
    }

    private static int[] gByte() {
        int[] b = new int[128];
        for (int i = 0; i < 128; i++) {
            b[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
        return b;
    }

    public static byte[] gBytes() {
        byte[] b = new byte[512];
        for (int i = 0; i < 512; i++) {
            b[i] = (byte) (new Random().nextInt(128));
        }
        return b;
    }

    private static List<byte[]> 初始化数据() {
        List<byte[]> blist = new ArrayList<>();

        for (int i = 0; i < 19; i++) {
            blist.add(gBytes());
        }
        return blist;
    }

    public final static int andox(int m) {

//        return map.get(1);

        return m;

    }

    private int[] 计算(byte[][] 生成数据, int[] 被比较数据) {

        int[] countArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18;
        int count0 = countArray[0],count1= countArray[1],count2= countArray[2],count3= countArray[3],count4= countArray[4],
                count5= countArray[5],count6=countArray[6],count7= countArray[7],count8= countArray[8],count9= countArray[9]
                ,count10= countArray[10],count11= countArray[11],count12= countArray[12],count13= countArray[13],
                count14= countArray[14],count15= countArray[15],count16= countArray[16],count17= countArray[17],
                count18= countArray[18];
        for (int s = 0; s < 512; s = s + 4) {

            d0 = 生成数据[0];
            d1 = 生成数据[1];
            d2 = 生成数据[2];
            d3 = 生成数据[3];
            d4 = 生成数据[4];
            d5 = 生成数据[5];
            d6 = 生成数据[6];
            d7 = 生成数据[7];
            d8 = 生成数据[8];
            d9 = 生成数据[9];
            d10 = 生成数据[10];
            d11 = 生成数据[11];
            d12 = 生成数据[12];
            d13 = 生成数据[13];
            d14 = 生成数据[14];
            d15 = 生成数据[15];
            d16 = 生成数据[16];
            d17 = 生成数据[17];
            d18 = 生成数据[18];
            count0 = count0 + andox((d0[s] + (d0[s + 1] << 8) + (d0[s + 2] << 16) + (d0[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count1 = count1 + andox((d1[s] + (d1[s + 1] << 8) + (d1[s + 2] << 16) + (d1[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count2 = count2 + andox((d2[s] + (d2[s + 1] << 8) + (d2[s + 2] << 16) + (d2[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count3 = count3 + andox((d3[s] + (d3[s + 1] << 8) + (d3[s + 2] << 16) + (d3[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count4 = count4 + andox((d4[s] + (d4[s + 1] << 8) + (d4[s + 2] << 16) + (d4[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count5 = count5 + andox((d5[s] + (d5[s + 1] << 8) + (d5[s + 2] << 16) + (d5[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count6 = count6 + andox((d6[s] + (d6[s + 1] << 8) + (d6[s + 2] << 16) + (d6[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count7 = count7 + andox((d7[s] + (d7[s + 1] << 8) + (d7[s + 2] << 16) + (d7[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count8 = count8 + andox((d8[s] + (d8[s + 1] << 8) + (d8[s + 2] << 16) + (d8[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count9 = count9 + andox((d9[s] + (d9[s + 1] << 8) + (d9[s + 2] << 16) + (d9[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count10 = count10 + andox((d10[s] + (d10[s + 1] << 8) + (d10[s + 2] << 16) + (d10[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count11 = count11 + andox((d11[s] + (d11[s + 1] << 8) + (d11[s + 2] << 16) + (d11[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count12 = count12 + andox((d12[s] + (d12[s + 1] << 8) + (d12[s + 2] << 16) + (d12[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count13 = count13 + andox((d13[s] + (d13[s + 1] << 8) + (d13[s + 2] << 16) + (d13[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count14 = count14 + andox((d14[s] + (d14[s + 1] << 8) + (d14[s + 2] << 16) + (d14[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count15 = count15 + andox((d15[s] + (d15[s + 1] << 8) + (d15[s + 2] << 16) + (d15[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count16 = count16 + andox((d16[s] + (d16[s + 1] << 8) + (d16[s + 2] << 16) + (d16[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count17 = count17 + andox((d17[s] + (d17[s + 1] << 8) + (d17[s + 2] << 16) + (d17[s + 3] << 24)) ^ 被比较数据[s / 4]);
            count18 = count18 + andox((d18[s] + (d18[s + 1] << 8) + (d18[s + 2] << 16) + (d18[s + 3] << 24)) ^ 被比较数据[s / 4]);
//
//            countArray[0] = countArray[0] + andox((d0[s] + (d0[s + 1] << 8) + (d0[s + 2] << 16) + (d0[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[1] = countArray[1] + andox((d1[s] + (d1[s + 1] << 8) + (d1[s + 2] << 16) + (d1[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[2] = countArray[2] + andox((d2[s] + (d2[s + 1] << 8) + (d2[s + 2] << 16) + (d2[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[3] = countArray[3] + andox((d3[s] + (d3[s + 1] << 8) + (d3[s + 2] << 16) + (d3[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[4] = countArray[4] + andox((d4[s] + (d4[s + 1] << 8) + (d4[s + 2] << 16) + (d4[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[5] = countArray[5] + andox((d5[s] + (d5[s + 1] << 8) + (d5[s + 2] << 16) + (d5[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[6] = countArray[6] + andox((d6[s] + (d6[s + 1] << 8) + (d6[s + 2] << 16) + (d6[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[7] = countArray[7] + andox((d7[s] + (d7[s + 1] << 8) + (d7[s + 2] << 16) + (d7[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[8] = countArray[8] + andox((d8[s] + (d8[s + 1] << 8) + (d8[s + 2] << 16) + (d8[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[9] = countArray[9] + andox((d9[s] + (d9[s + 1] << 8) + (d9[s + 2] << 16) + (d9[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[10] = countArray[10] + andox((d10[s] + (d10[s + 1] << 8) + (d10[s + 2] << 16) + (d10[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[11] = countArray[11] + andox((d11[s] + (d11[s + 1] << 8) + (d11[s + 2] << 16) + (d11[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[12] = countArray[12] + andox((d12[s] + (d12[s + 1] << 8) + (d12[s + 2] << 16) + (d12[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[13] = countArray[13] + andox((d13[s] + (d13[s + 1] << 8) + (d13[s + 2] << 16) + (d13[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[14] = countArray[14] + andox((d14[s] + (d14[s + 1] << 8) + (d14[s + 2] << 16) + (d14[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[15] = countArray[15] + andox((d15[s] + (d15[s + 1] << 8) + (d15[s + 2] << 16) + (d15[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[16] = countArray[16] + andox((d16[s] + (d16[s + 1] << 8) + (d16[s + 2] << 16) + (d16[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[17] = countArray[17] + andox((d17[s] + (d17[s + 1] << 8) + (d17[s + 2] << 16) + (d17[s + 3] << 24)) ^ 被比较数据[s / 4]);
//            countArray[18] = countArray[18] + andox((d18[s] + (d18[s + 1] << 8) + (d18[s + 2] << 16) + (d18[s + 3] << 24)) ^ 被比较数据[s / 4]);

        }
        return countArray;
    }

    public static void main(String asd[]) throws InterruptedException {
//        System.out.println(Integer.MAX_VALUE);
//        int test[] = new int[Integer.MAX_VALUE -2];
        map.put(1, 1);
        int[] ints = gByte();
        byte[][] list = 初始化数据1();
        四位一组 ll = new 四位一组();
        Thread.sleep(1000);
        int sun=0;
        long t1 = System.currentTimeMillis();
        for (int m = 0; m < 500000*512*19; m++) {
            sun=sun+kk[m];
        }
        System.out.println((System.currentTimeMillis() - t1)+"---"+sun);


        long t11 = System.currentTimeMillis();
        for (int m = 0; m < 500000; m++) {
            int[] jk = ll.计算(list, ints);
        }
        System.out.println(System.currentTimeMillis() - t11);

    }
}

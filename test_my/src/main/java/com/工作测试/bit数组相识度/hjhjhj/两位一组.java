package com.工作测试.bit数组相识度.hjhjhj;

import java.util.*;

public class 两位一组 {

    private static TreeSet<Integer> tree = new TreeSet<>();
    private static Map<Integer, Integer> map = new HashMap<>(2144);

    private static byte[][] 初始化数据1() {
        byte[][] blist = new byte[19][512];

        for (int i = 0; i < 19; i++) {
            blist[i] = gBytes();
        }
        return blist;
    }

    public static int[] kk = new int[Integer.MAX_VALUE / 3];
    public static short[] kkl = new short[10];

    static {
        kk[1] = 1254635;
        kkl[1]=16456;

    }

    private static short[] gByte() {
        short[] b = new short[128];
        for (int i = 0; i < 128; i++) {
            if ((int) Math.random() * 10 > 5) {
                b[i] = (short) (Math.random() * Short.MAX_VALUE * -1);
            } else {
                b[i] = (short) (Math.random() * Short.MAX_VALUE);
            }
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

    public short andox(short m) {

//        return map.get(1);
            return kkl[m];

    }

    private short[] 计算(byte[][] 生成数据, short[] 被比较数据) {

        short[] countArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18;

        for (int s = 0; s < 512; s = s + 2) {

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

            countArray[0] = (short) (countArray[0] + andox((short) 1));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));

            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));

            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));

            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));

            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));
//            countArray[0] = (short) (countArray[0] + andox((short) ((d0[s] + (d0[s + 1] << 8)) ^ 被比较数据[s / 2])));

        }
        return countArray;
    }

    public static void main(String asd[]) throws InterruptedException {
//        System.out.println(Integer.MAX_VALUE);
//        int test[] = new int[Integer.MAX_VALUE -2];
        map.put(1, 1);
        short[] ints = gByte();
        byte[][] list = 初始化数据1();
        两位一组 ll = new 两位一组();
        Thread.sleep(1000);
        long t1 = System.currentTimeMillis();
        for (int m = 0; m < 500000; m++) {
            short[] jk = ll.计算(list, ints);
        }
        System.out.println(System.currentTimeMillis() - t1);

    }
}

package com.api.test;

public class 终极开挂 {
    private final static int toPlus = 128;
    private final static int addOne = 1;
    private byte[] myBi1 = null, myBi2 = null, myBi3 = null,
            myBi4 = null, myBi5 = null, myBi6 = null,
            myBi7 = null, myBi8 = null, myBi9 = null,
            myBi10 = null, myBi11 = null, myBi12 = null,
            myBi13 = null, myBi14 = null, myBi15 = null,
            myBi16 = null, myBi17 = null, myBi18 = null,
            myBi19 = null;

    private void 散列数组(byte[][] 生成数据) {
        myBi1 = 生成数据[0];
        myBi2 = 生成数据[1];
        myBi3 = 生成数据[2];
        myBi4 = 生成数据[3];
        myBi5 = 生成数据[4];
        myBi6 = 生成数据[5];
        myBi7 = 生成数据[6];
        myBi8 = 生成数据[7];
        myBi9 = 生成数据[8];
        myBi10 = 生成数据[9];
        myBi11 = 生成数据[10];
        myBi12 = 生成数据[11];
        myBi13 = 生成数据[12];
        myBi14 = 生成数据[13];
        myBi15 = 生成数据[14];
        myBi16 = 生成数据[15];
        myBi17 = 生成数据[16];
        myBi18 = 生成数据[17];
        myBi19 = 生成数据[18];
    }


    private static byte[] gByte() {
        byte[] b = new byte[512];
        for (int i = 0; i < 512; i++) {
            b[i] = (byte) (Math.random() * 128);
        }
        return b;
    }

    public static int[][] memory = new int[256][256];

    static {
        for (int kk = 0; kk < 256; kk++) {
            for (int jj = 0; jj < 256; jj++) {
                memory[kk][jj] = (int) (Math.random() * 128);
            }
        }
    }


    private static byte[][] 初始化数据1() {
        byte[][] blist = new byte[19][512];

        for (int i = 0; i < 19; i++) {
            blist[i] = gByte();
        }
        return blist;
    }


    private int 计算(byte[] 被比较数据) {

        int[] memory1 = null, memory2 = null, memory3 = null, memory4 = null,
                memory5 = null, memory6 = null, memory7 = null, memory8 = null;

        int count0 = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0, count9 = 0,
                count10 = 0, count11 = 0, count12 = 0, count13 = 0, count14 = 0, count15 = 0, count16 = 0, count17 = 0, count18 = 0;

        for (int s = 0,d=1,f=2,g=3,h=4,j=5,k=6,l=7; s < 504; s+=8,d+=8,f+=8,g+=8,h+=8,j+=8,k+=8,l+=8) {

            memory1 = memory[被比较数据[s]];
            memory2 = memory[被比较数据[d]];
            memory3 = memory[被比较数据[s + 2]];
            memory4 = memory[被比较数据[s + 3]];
            memory5 = memory[被比较数据[s + 4]];
            memory6 = memory[被比较数据[s + 5]];
            memory7 = memory[被比较数据[s + 6]];
            memory8 = memory[被比较数据[s + 7]];

            count0 = count0 + (memory1[myBi1[s]] + memory2[myBi1[s + 1]]);
            count0 = count0 + (memory3[myBi1[s + 2]] + memory4[myBi1[s + 3]]);
            count0 = count0 + (memory5[myBi1[s + 4]] + memory6[myBi1[s + 5]]);
            count0 = count0 + (memory7[myBi1[s + 6]] + memory8[myBi1[s + 7]]);

            count1 = count1 + (memory1[myBi2[s + 1]] + memory2[myBi2[s + 2]]);
            count1 = count1 + (memory3[myBi2[s + 3]] + memory4[myBi2[s + 4]]);
            count1 = count1 + (memory5[myBi2[s + 5]] + memory6[myBi2[s + 6]]);
            count1 = count1 + (memory7[myBi2[s + 7]] + memory8[myBi2[s + 8]]);

            count2 = count2 + (memory1[myBi3[s + 1]] + memory2[myBi3[s + 2]]);
            count2 = count2 + (memory3[myBi3[s + 3]] + memory4[myBi3[s + 4]]);
            count2 = count2 + (memory5[myBi3[s + 5]] + memory6[myBi3[s + 6]]);
            count2 = count2 + (memory7[myBi3[s + 7]] + memory8[myBi3[s + 8]]);

            count3 = count3 + (memory1[myBi4[s + 1]] + memory2[myBi4[s + 2]]);
            count3 = count3 + (memory3[myBi4[s + 3]] + memory4[myBi4[s + 4]]);
            count3 = count3 + (memory5[myBi4[s + 5]] + memory6[myBi4[s + 6]]);
            count3 = count3 + (memory7[myBi4[s + 7]] + memory8[myBi4[s + 8]]);

            count4 = count4 + (memory1[myBi5[s]] + memory2[myBi5[s + 1]]);
            count4 = count4 + (memory3[myBi5[s + 2]] + memory4[myBi5[s + 3]]);
            count4 = count4 + (memory5[myBi5[s + 4]] + memory6[myBi5[s + 5]]);
            count4 = count4 + (memory7[myBi5[s + 6]] + memory8[myBi5[s + 7]]);

            count5 = count5 + (memory1[myBi6[s]] + memory2[myBi6[s + 1]]);
            count5 = count5 + (memory3[myBi6[s + 2]] + memory4[myBi6[s + 3]]);
            count5 = count5 + (memory5[myBi6[s + 4]] + memory6[myBi6[s + 5]]);
            count5 = count5 + (memory7[myBi6[s + 6]] + memory8[myBi6[s + 7]]);

            count6 = count6 + (memory1[myBi7[s]] + memory2[myBi7[s + 1]]);
            count6 = count6 + (memory3[myBi7[s + 2]] + memory4[myBi7[s + 3]]);
            count6 = count6 + (memory5[myBi7[s + 4]] + memory6[myBi7[s + 5]]);
            count6 = count6 + (memory7[myBi7[s + 6]] + memory8[myBi7[s + 7]]);

            count7 = count7 + (memory1[myBi8[s]] + memory2[myBi8[s + 1]]);
            count7 = count7 + (memory3[myBi8[s + 2]] + memory4[myBi8[s + 3]]);
            count7 = count7 + (memory5[myBi8[s + 4]] + memory6[myBi8[s + 5]]);
            count7 = count7 + (memory7[myBi8[s + 6]] + memory8[myBi8[s + 7]]);

            count8 = count8 + (memory1[myBi9[s]] + memory2[myBi9[s + 1]]);
            count8 = count8 + (memory3[myBi9[s + 2]] + memory4[myBi9[s + 3]]);
            count8 = count8 + (memory5[myBi9[s + 4]] + memory6[myBi9[s + 5]]);
            count8 = count8 + (memory7[myBi9[s + 6]] + memory8[myBi9[s + 7]]);

            count9 = count9 + (memory1[myBi10[s]] + memory2[myBi10[s + 1]]);
            count9 = count9 + (memory3[myBi10[s + 2]] + memory4[myBi10[s + 3]]);
            count9 = count9 + (memory5[myBi10[s + 4]] + memory6[myBi10[s + 5]]);
            count9 = count9 + (memory7[myBi10[s + 6]] + memory8[myBi10[s + 7]]);

            count10 = count10 + (memory1[myBi11[s]] + memory2[myBi11[s + 1]]);
            count10 = count10 + (memory3[myBi11[s + 2]] + memory4[myBi11[s + 3]]);
            count10 = count10 + (memory5[myBi11[s + 4]] + memory6[myBi11[s + 5]]);
            count10 = count10 + (memory7[myBi11[s + 6]] + memory8[myBi11[s + 7]]);

            count11 = count11 + (memory1[myBi12[s]] + memory2[myBi12[s + 1]]);
            count11 = count11 + (memory3[myBi12[s + 2]] + memory4[myBi12[s + 3]]);
            count11 = count11 + (memory5[myBi12[s + 4]] + memory6[myBi12[s + 5]]);
            count11 = count11 + (memory7[myBi12[s + 6]] + memory8[myBi12[s + 7]]);

            count12 = count12 + (memory1[myBi13[s]] + memory2[myBi13[s + 1]]);
            count12 = count12 + (memory3[myBi13[s + 2]] + memory4[myBi13[s + 3]]);
            count12 = count12 + (memory5[myBi13[s + 4]] + memory6[myBi13[s + 5]]);
            count12 = count12 + (memory7[myBi13[s + 6]] + memory8[myBi13[s + 7]]);

            count13 = count13 + (memory1[myBi14[s]] + memory2[myBi14[s + 1]]);
            count13 = count13 + (memory3[myBi14[s + 2]] + memory4[myBi14[s + 3]]);
            count13 = count13 + (memory5[myBi14[s + 4]] + memory6[myBi14[s + 5]]);
            count13 = count13 + (memory7[myBi14[s + 6]] + memory8[myBi14[s + 7]]);

            count14 = count14 + (memory1[myBi15[s]] + memory2[myBi15[s + 1]]);
            count14 = count14 + (memory3[myBi15[s + 2]] + memory4[myBi15[s + 3]]);
            count14 = count14 + (memory5[myBi15[s + 4]] + memory6[myBi15[s + 5]]);
            count14 = count14 + (memory7[myBi15[s + 6]] + memory8[myBi15[s + 7]]);

            count15 = count15 + (memory1[myBi16[s]] + memory2[myBi16[s + 1]]);
            count15 = count15 + (memory3[myBi16[s + 2]] + memory4[myBi16[s + 3]]);
            count15 = count15 + (memory5[myBi16[s + 4]] + memory6[myBi16[s + 5]]);
            count15 = count15 + (memory7[myBi16[s + 6]] + memory8[myBi16[s + 7]]);

            count16 = count16 + (memory1[myBi17[s]] + memory2[myBi17[s + 1]]);
            count16 = count16 + (memory3[myBi17[s + 2]] + memory4[myBi17[s + 3]]);
            count16 = count16 + (memory5[myBi17[s + 4]] + memory6[myBi17[s + 5]]);
            count16 = count16 + (memory7[myBi17[s + 6]] + memory8[myBi17[s + 7]]);

            count17 = count17 + (memory1[myBi18[s]] + memory2[myBi18[s + 1]]);
            count17 = count17 + (memory3[myBi18[s + 2]] + memory4[myBi18[s + 3]]);
            count17 = count17 + (memory5[myBi18[s + 4]] + memory6[myBi18[s + 5]]);
            count17 = count17 + (memory7[myBi18[s + 6]] + memory8[myBi18[s + 7]]);

            count18 = count18 + (memory1[myBi19[s]] + memory2[myBi19[s + 1]]);
            count18 = count18 + (memory3[myBi19[s + 2]] + memory4[myBi19[s + 3]]);
            count18 = count18 + (memory5[myBi19[s + 4]] + memory6[myBi19[s + 5]]);
            count18 = count18 + (memory7[myBi19[s + 6]] + memory8[myBi19[s + 7]]);
        }

        int[] countArray = {count0, count1, count2, count3, count4, count5, count6, count7, count8, count9, count10, count11, count12, count13, count14, count15, count16, count17, count18};
        return getMax(countArray);
    }

    private int getMax(int[] jk) {
        int s = 0;
        for (int g = 0; g < 19; g++) {
            if (jk[g] > s) {
                s = jk[g];
            }
        }
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        byte[] ints = gByte();
        终极开挂 ll = new 终极开挂();
        byte[][] 生成数据 = 初始化数据1();
        ll.散列数组(生成数据);
//        Thread.sleep(100L);
        long t11 = System.currentTimeMillis();
        for (int m = 0; m < 500000; m++) {
            int jk = ll.计算(ints);
        }
        System.out.println(System.currentTimeMillis() - t11);

    }


}

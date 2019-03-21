package com.工作测试.bit数组相识度.hjhjhj;

public class 终极开挂 {
//    private final static int 128 = 128;
    private final static int addOne = 1;
    private byte[] myBiOne = null, myBiTwo = null, myBiThree = null,
            myBiFour = null, myBiFree = null, myBiSix = null,
            myBiSenven = null, myBiBa = null, myBi9 = null,
            myBi10 = null, myBi11 = null, myBi12 = null,
            myBi13 = null, myBi14 = null, myBi15 = null,
            myBi16 = null, myBi17 = null, myBi18 = null,
            myBi19 = null;

    private void 散列数组(byte[][] 生成数据) {
        myBiOne = 生成数据[0];
        myBiTwo = 生成数据[1];
        myBiThree = 生成数据[2];
        myBiFour = 生成数据[3];
        myBiFree = 生成数据[4];
        myBiSix = 生成数据[5];
        myBiSenven = 生成数据[6];
        myBiBa = 生成数据[7];
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
            b[i] = (byte) (Math.random() > 0.5 ? 1 : 0);
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
                memory5 = null, memory6 = null, memory7 = null, memory8 = null,
                memory9 = null, memory10 = null, memory11 = null, memory12 = null,
                memory13 = null, memory14 = null, memory15 = null, memory16 = null;

        int count0 = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0, count9 = 0,
                count10 = 0, count11 = 0, count12 = 0, count13 = 0, count14 = 0, count15 = 0, count16 = 0, count17 = 0, count18 = 0;

        for (int s = 0; s < 512; s = s + 16) {

            memory1 = memory[被比较数据[s]];
            memory2 = memory[被比较数据[s + 1]];
            memory3 = memory[被比较数据[s + 2]];
            memory4 = memory[被比较数据[s + 3]];
            memory5 = memory[被比较数据[s + 4]];
            memory6 = memory[被比较数据[s + 5]];
            memory7 = memory[被比较数据[s + 6]];
            memory8 = memory[被比较数据[s + 7]];
            memory9 = memory[被比较数据[s + 8]];
            memory10 = memory[被比较数据[s + 9]];
            memory11 = memory[被比较数据[s + 10]];
            memory12 = memory[被比较数据[s + 11]];
            memory13 = memory[被比较数据[s + 12]];
            memory14 = memory[被比较数据[s + 13]];
            memory15 = memory[被比较数据[s + 14]];
            memory16 = memory[被比较数据[s + 15]];

            count0 = count0 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count0 = count0 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count0 = count0 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count0 = count0 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count0 = count0 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count0 = count0 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count0 = count0 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count0 = count0 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count1 = count1 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count1 = count1 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count1 = count1 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count1 = count1 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count1 = count1 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count1 = count1 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count1 = count1 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count1 = count1 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count2 = count2 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count2 = count2 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count2 = count2 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count2 = count2 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count2 = count2 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count2 = count2 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count2 = count2 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count2 = count2 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count3 = count3 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count3 = count3 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count3 = count3 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count3 = count3 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count3 = count3 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count3 = count3 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count3 = count3 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count3 = count3 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count4 = count4 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count4 = count4 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count4 = count4 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count4 = count4 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count4 = count4 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count4 = count4 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count4 = count4 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count4 = count4 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count5 = count5 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count5 = count5 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count5 = count5 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count5 = count5 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count5 = count5 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count5 = count5 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count5 = count5 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count5 = count5 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count6 = count6 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count6 = count6 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count6 = count6 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count6 = count6 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count6 = count6 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count6 = count6 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count6 = count6 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count6 = count6 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count7 = count7 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count7 = count7 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count7 = count7 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count7 = count7 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count7 = count7 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count7 = count7 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count7 = count7 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count7 = count7 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count8 = count8 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count8 = count8 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count8 = count8 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count8 = count8 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count8 = count8 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count8 = count8 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count8 = count8 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count8 = count8 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count9 = count9 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count9 = count9 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count9 = count9 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count9 = count9 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count9 = count9 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count9 = count9 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count9 = count9 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count9 = count9 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count10 = count10 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count10 = count10 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count10 = count10 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count10 = count10 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count10 = count10 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count10 = count10 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count10 = count10 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count10 = count10 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count11 = count11 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count11 = count11 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count11 = count11 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count11 = count11 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count11 = count11 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count11 = count11 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count11 = count11 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count11 = count11 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count12 = count12 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count12 = count12 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count12 = count12 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count12 = count12 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count12 = count12 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count12 = count12 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count12 = count12 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count12 = count12 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count13 = count13 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count13 = count13 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count13 = count13 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count13 = count13 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count13 = count13 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count13 = count13 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count13 = count13 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count13 = count13 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count14 = count14 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count14 = count14 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count14 = count14 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count14 = count14 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count14 = count14 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count14 = count14 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count14 = count14 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count14 = count14 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count15 = count15 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count15 = count15 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count15 = count15 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count15 = count15 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count15 = count15 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count15 = count15 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count15 = count15 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count15 = count15 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count16 = count16 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count16 = count16 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count16 = count16 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count16 = count16 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count16 = count16 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count16 = count16 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count16 = count16 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count16 = count16 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count17 = count17 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count17 = count17 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count17 = count17 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count17 = count17 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count17 = count17 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count17 = count17 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count17 = count17 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count17 = count17 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
            count18 = count18 + (memory1[myBiOne[s] + 128] + memory2[myBiOne[s + 1] + 128]);
            count18 = count18 + (memory3[myBiOne[s + 2] + 128] + memory4[myBiOne[s + 3] + 128]);
            count18 = count18 + (memory5[myBiOne[s + 4] + 128] + memory6[myBiOne[s + 5] + 128]);
            count18 = count18 + (memory7[myBiOne[s + 6] + 128] + memory8[myBiOne[s + 7] + 128]);
            count18 = count18 + (memory9[myBiOne[s + 8] + 128] + memory10[myBiOne[s + 9] + 128]);
            count18 = count18 + (memory11[myBiOne[s + 10] + 128] + memory12[myBiOne[s + 11] + 128]);
            count18 = count18 + (memory13[myBiOne[s + 12] + 128] + memory14[myBiOne[s + 13] + 128]);
            count18 = count18 + (memory15[myBiOne[s + 14] + 128] + memory16[myBiOne[s + 15] + 128]);
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
        ll.散列数组(生成数据);
        for (int m = 0; m < 500000; m++) {
            int jk = ll.计算(ints);
        }
        System.out.println(System.currentTimeMillis() - t11);
    }

}

package com.工作测试.bit数组相识度.hjhjhj;

public class 终极开挂2 {

    private static byte[] gByte() {
        byte[] b = new byte[512];
        for (int i = 0; i < 512; i++) {
            b[i] = (byte) (Math.random() > 0.5 ? 1 : 0);
        }
        return b;
    }

    public static int[][] memory = new int[258][258];

    private static byte[][] 初始化数据1() {
        byte[][] blist = new byte[19][512];

        for (int i = 0; i < 19; i++) {
            blist[i] = gByte();
        }
        return blist;
    }

    private int[] 计算(byte[][] 生成数据, byte[] 被比较数据) {

        int[] countArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int count0 = countArray[0], count1 = countArray[1], count2 = countArray[2], count3 = countArray[3], count4 = countArray[4],
                count5 = countArray[5], count6 = countArray[6], count7 = countArray[7], count8 = countArray[8], count9 = countArray[9], count10 = countArray[10], count11 = countArray[11], count12 = countArray[12], count13 = countArray[13],
                count14 = countArray[14], count15 = countArray[15], count16 = countArray[16], count17 = countArray[17],
                count18 = countArray[18],m,mm;
        for (int s = 0; s < 512; s = s+2) {

            m = 被比较数据[s];
            mm = 被比较数据[s + 1];
            count0 = count0 + memory[m][生成数据[0][s]];
            count0 = count0 +memory[mm][生成数据[0][s+1] + 128];
            count1 = count1 + memory[m][生成数据[1][s]];
            count1 = count1 +memory[mm][生成数据[1][s+1] + 128];
            count2 = count2 + memory[m][生成数据[2][s]];
            count2 = count2 +memory[mm][生成数据[2][s+1] + 128];
            count3 = count3 + memory[m][生成数据[3][s]];
            count3 = count3 +memory[mm][生成数据[3][s+1] + 128];
            count4 = count4 + memory[m][生成数据[4][s]];
            count4 = count4 +memory[mm][生成数据[4][s+1] + 128];
            count5 = count5 + memory[m][生成数据[5][s]];
            count5 = count5 +memory[mm][生成数据[5][s+1] + 128];
            count6 = count6 + memory[m][生成数据[6][s]];
            count6 = count6 +memory[mm][生成数据[6][s+1] + 128];
            count7 = count7 + memory[m][生成数据[7][s]];
            count7 = count7 +memory[mm][生成数据[7][s+1] + 128];
            count8 = count8 + memory[m][生成数据[8][s]];
            count8 = count8 + memory[mm][生成数据[8][s+1] + 128];
            count9 = count9 + memory[m][生成数据[9][s]];
            count9 = count9 +memory[mm][生成数据[9][s+1] + 128];
            count10 = count10 + memory[m][生成数据[10][s]];
            count10 = count10 +memory[mm][生成数据[10][s+1] + 128];
            count11 = count11 + memory[m][生成数据[11][s]];
            count11 = count11 +memory[mm][生成数据[11][s+1] + 128];
            count12 = count12 + memory[m][生成数据[12][s]];
            count12 = count12 +memory[mm][生成数据[12][s+1] + 128];
            count13 = count13 + memory[m][生成数据[13][s]];
            count13 = count13 +memory[mm][生成数据[13][s+1] + 128];
            count14 = count14 + memory[m][生成数据[14][s]];
            count14 = count14 +memory[mm][生成数据[14][s+1] + 128];
            count15 = count15 + memory[m][生成数据[15][s]];
            count15 = count15 +memory[mm][生成数据[15][s+1] + 128];
            count16 = count16 + memory[m][生成数据[16][s]];
            count16 = count16 +memory[mm][生成数据[16][s+1] + 128];
            count17 = count17 + memory[m][生成数据[17][s]];
            count17 = count17 +memory[mm][生成数据[17][s+1] + 128];
            count18 = count18 + memory[m][生成数据[18][s]];
            count18 = count18 +memory[mm][生成数据[18][s+1] + 128];

        }
        return countArray;
    }

    public static void main(String[] args) {
        byte[] ints = gByte();
        byte[][] list = 初始化数据1();
        终极开挂2 ll = new 终极开挂2();
        long t11 = System.currentTimeMillis();
        for (int m = 0; m < 500000; m++) {
            int[] jk = ll.计算(list, ints);
        }
        System.out.println(System.currentTimeMillis() - t11);

    }
}

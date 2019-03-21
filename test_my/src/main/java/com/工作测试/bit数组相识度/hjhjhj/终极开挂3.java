package com.工作测试.bit数组相识度.hjhjhj;

public class 终极开挂3 {
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


    private static byte[][] 初始化数据1() {
        byte[][] blist = new byte[19][512];

        for (int i = 0; i < 19; i++) {
            blist[i] = gByte();
        }
        return blist;
    }

    private int abs( int x )
    {
        int y ;
        y = x >> 31 ;
        return (x^y)-y ;
    }

    private int 计算(byte[] 被比较数据) {

        int[] countArray = new int[19];
        int[] memoryOne = null, memoryTwo = null;

        for (int s = 0; s < 512; s = s + 2) {

            memoryOne = memory[被比较数据[s]];
            memoryTwo = memory[被比较数据[s + 1]];

            countArray[0] = countArray[0] + (memoryOne[myBiOne[s] + 128] + memoryTwo[myBiOne[s + 1] + 128]);
            countArray[1] = countArray[1] + (memoryOne[myBiTwo[s] + 128] + memoryTwo[myBiTwo[s + 1] + 128]);
            countArray[2] = countArray[2] + (memoryOne[myBiThree[s] + 128] + memoryTwo[myBiThree[s + 1] + 128]);
            countArray[3] = countArray[3] + (memoryOne[myBiFour[s] + 128] + memoryTwo[myBiFour[s + 1] + 128]);
            countArray[4] = countArray[4] + (memoryOne[myBiFree[s] + 128] + memoryTwo[myBiFree[s + 1] + 128]);
            countArray[5] = countArray[5] + (memoryOne[myBiSix[s] + 128] + memoryTwo[myBiSix[s + 1] + 128]);
            countArray[6] = countArray[6] + (memoryOne[myBiSenven[s] + 128] + memoryTwo[myBiSenven[s + 1] + 128]);
            countArray[7] = countArray[7] + (memoryOne[myBiBa[s] + 128] + memoryTwo[myBiBa[s + 1] + 128]);
            countArray[8] = countArray[8] + (memoryOne[myBi9[s] + 128] + memoryTwo[myBi9[s + 1] + 128]);
            countArray[9] = countArray[9] + (memoryOne[myBi10[s] + 128] + memoryTwo[myBi10[s + 1] + 128]);
            countArray[10] = countArray[10] + (memoryOne[myBi11[s] + 128] + memoryTwo[myBi11[s + 1] + 128]);
            countArray[11] = countArray[11] + (memoryOne[myBi12[s] + 128] + memoryTwo[myBi12[s + 1] + 128]);
            countArray[12] = countArray[12] + (memoryOne[myBi13[s] + 128] + memoryTwo[myBi13[s + 1] + 128]);
            countArray[13] = countArray[13] + (memoryOne[myBi14[s] + 128] + memoryTwo[myBi14[s + 1] + 128]);
            countArray[14] = countArray[14] + (memoryOne[myBi15[s] + 128] + memoryTwo[myBi15[s + 1] + 128]);
            countArray[15] = countArray[15] + (memoryOne[myBi16[s] + 128] + memoryTwo[myBi16[s + 1] + 128]);
            countArray[16] = countArray[16] + (memoryOne[myBi17[s] + 128] + memoryTwo[myBi17[s + 1] + 128]);
            countArray[17] = countArray[17] + (memoryOne[myBi18[s] + 128] + memoryTwo[myBi18[s + 1] + 128]);
            countArray[18] = countArray[18] + (memoryOne[myBi19[s] + 128] + memoryTwo[myBi19[s + 1] + 128]);
        }
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

    public static void main(String[] args) {
        byte[] ints = gByte();
        byte[][] 生成数据 = 初始化数据1();
        终极开挂3 ll = new 终极开挂3();

        long t11 = System.currentTimeMillis();

        for (int m = 0; m < 500000; m++) {
            ll.散列数组(生成数据);
            int jk = ll.计算(ints);
        }
        System.out.println(System.currentTimeMillis() - t11);

    }


}

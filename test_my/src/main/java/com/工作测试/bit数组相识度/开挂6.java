package com.工作测试.bit数组相识度;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class 开挂6 {


    private static byte[][] 初始化数据() {
        byte[][] blist = new byte[19][512];

        for (int i = 0; i < 19; i++) {
            blist[i] = gBytes();
        }
        return blist;
    }

    private static byte[] gByte() {
        byte[] b = new byte[128];
        for (int i = 0; i < 128; i++) {
            b[i] = (byte) (Math.random() * 10 % 4);
        }
        return b;
    }

    private static byte[] gBytes() {
        byte[] b = new byte[512];
        for (int i = 0; i < 512; i++) {
            b[i] = (byte) (Math.random() > 0.5 ? 1 : 0);
        }
        return b;
    }

    public static void main(String[] args) throws InterruptedException {

        byte[][] 生成数据 = 初始化数据();

        byte[] 被比较数据 = gByte();
        final  int n = 1;
        final CountDownLatch latch = new CountDownLatch(n);

        long t1 = System.currentTimeMillis();
        for (int t=0;t<n;t++){
            new Thread() {
                @Override
                public void run() {
                    开挂6 ll = new 开挂6();
                    for (int m = 0; m < 500000; m++) {
                        int[] jk = ll.计算(生成数据, 被比较数据);
                    }
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
        System.out.println(System.currentTimeMillis() - t1);
    }


    private  int[] 计算(byte[][] 生成数据, byte[] 被比较数据) {

        int[] countArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int qs = 0;

        for (int s = 0; s < 512; s = s + 2) {
            qs = s/2;
            if (被比较数据[qs] == 0) {
                continue;
            }

            if (被比较数据[qs] == 1) {
                if (生成数据[0][s] == 1) {
                    countArray[0]++;
                }
                if (生成数据[1][s] == 1) {
                    countArray[1]++;
                }
                if (生成数据[2][s] == 1) {
                    countArray[2]++;
                }
                if (生成数据[3][s] == 1) {
                    countArray[3]++;
                }
                if (生成数据[4][s] == 1) {
                    countArray[4]++;
                }
                if (生成数据[5][s] == 1) {
                    countArray[5]++;
                }
                if (生成数据[6][s] == 1) {
                    countArray[6]++;
                }
                if (生成数据[7][s] == 1) {
                    countArray[7]++;
                }
                if (生成数据[8][s] == 1) {
                    countArray[8]++;
                }
                if (生成数据[9][s] == 1) {
                    countArray[9]++;
                }
                if (生成数据[10][s] == 1) {
                    countArray[10]++;
                }
                if (生成数据[11][s] == 1) {
                    countArray[11]++;
                }
                if (生成数据[12][s] == 1) {
                    countArray[12]++;
                }
                if (生成数据[13][s] == 1) {
                    countArray[13]++;
                }
                if (生成数据[14][s] == 1) {
                    countArray[14]++;
                }
                if (生成数据[15][s] == 1) {
                    countArray[15]++;
                }
                if (生成数据[16][s] == 1) {
                    countArray[16]++;
                }
                if (生成数据[17][s] == 1) {
                    countArray[17]++;
                }
                if (生成数据[18][s] == 1) {
                    countArray[18]++;
                }
                continue;

            }
            if (被比较数据[qs] == 2) {
                if (生成数据[0][s + 1] == 1) {
                    countArray[0]++;
                }
                if (生成数据[1][s + 1] == 1) {
                    countArray[1]++;
                }
                if (生成数据[2][s + 1] == 1) {
                    countArray[2]++;
                }
                if (生成数据[3][s + 1] == 1) {
                    countArray[3]++;
                }
                if (生成数据[4][s + 1] == 1) {
                    countArray[4]++;
                }
                if (生成数据[5][s + 1] == 1) {
                    countArray[5]++;
                }
                if (生成数据[6][s + 1] == 1) {
                    countArray[6]++;
                }
                if (生成数据[7][s + 1] == 1) {
                    countArray[7]++;
                }
                if (生成数据[8][s + 1] == 1) {
                    countArray[8]++;
                }
                if (生成数据[9][s + 1] == 1) {
                    countArray[9]++;
                }
                if (生成数据[10][s + 1] == 1) {
                    countArray[10]++;
                }
                if (生成数据[11][s + 1] == 1) {
                    countArray[11]++;
                }
                if (生成数据[12][s + 1] == 1) {
                    countArray[12]++;
                }
                if (生成数据[13][s + 1] == 1) {
                    countArray[13]++;
                }
                if (生成数据[14][s + 1] == 1) {
                    countArray[14]++;
                }
                if (生成数据[15][s + 1] == 1) {
                    countArray[15]++;
                }
                if (生成数据[16][s + 1] == 1) {
                    countArray[16]++;
                }
                if (生成数据[17][s + 1] == 1) {
                    countArray[17]++;
                }
                if (生成数据[18][s + 1] == 1) {
                    countArray[18]++;
                }
                if (生成数据[0][s] == 1) {
                    countArray[0]++;
                }
                if (生成数据[1][s] == 1) {
                    countArray[1]++;
                }
                if (生成数据[2][s] == 1) {
                    countArray[2]++;
                }
                if (生成数据[3][s] == 1) {
                    countArray[3]++;
                }
                if (生成数据[4][s] == 1) {
                    countArray[4]++;
                }
                if (生成数据[5][s] == 1) {
                    countArray[5]++;
                }
                if (生成数据[6][s] == 1) {
                    countArray[6]++;
                }
                if (生成数据[7][s] == 1) {
                    countArray[7]++;
                }
                if (生成数据[8][s] == 1) {
                    countArray[8]++;
                }
                if (生成数据[9][s] == 1) {
                    countArray[9]++;
                }
                if (生成数据[10][s] == 1) {
                    countArray[10]++;
                }
                if (生成数据[11][s] == 1) {
                    countArray[11]++;
                }
                if (生成数据[12][s] == 1) {
                    countArray[12]++;
                }
                if (生成数据[13][s] == 1) {
                    countArray[13]++;
                }
                if (生成数据[14][s] == 1) {
                    countArray[14]++;
                }
                if (生成数据[15][s] == 1) {
                    countArray[15]++;
                }
                if (生成数据[16][s] == 1) {
                    countArray[16]++;
                }
                if (生成数据[17][s] == 1) {
                    countArray[17]++;
                }
                if (生成数据[18][s] == 1) {
                    countArray[18]++;
                }
                continue;
            }
            if (被比较数据[qs] == 3) {
                if (生成数据[0][s + 1] == 1) {
                    countArray[0]++;
                }
                if (生成数据[1][s + 1] == 1) {
                    countArray[1]++;
                }
                if (生成数据[2][s + 1] == 1) {
                    countArray[2]++;
                }
                if (生成数据[3][s + 1] == 1) {
                    countArray[3]++;
                }
                if (生成数据[4][s + 1] == 1) {
                    countArray[4]++;
                }
                if (生成数据[5][s + 1] == 1) {
                    countArray[5]++;
                }
                if (生成数据[6][s + 1] == 1) {
                    countArray[6]++;
                }
                if (生成数据[7][s + 1] == 1) {
                    countArray[7]++;
                }
                if (生成数据[8][s + 1] == 1) {
                    countArray[8]++;
                }
                if (生成数据[9][s + 1] == 1) {
                    countArray[9]++;
                }
                if (生成数据[10][s + 1] == 1) {
                    countArray[10]++;
                }
                if (生成数据[11][s + 1] == 1) {
                    countArray[11]++;
                }
                if (生成数据[12][s + 1] == 1) {
                    countArray[12]++;
                }
                if (生成数据[13][s + 1] == 1) {
                    countArray[13]++;
                }
                if (生成数据[14][s + 1] == 1) {
                    countArray[14]++;
                }
                if (生成数据[15][s + 1] == 1) {
                    countArray[15]++;
                }
                if (生成数据[16][s + 1] == 1) {
                    countArray[16]++;
                }
                if (生成数据[17][s + 1] == 1) {
                    countArray[17]++;
                }
                if (生成数据[18][s + 1] == 1) {
                    countArray[18]++;
                }
            }
        }
        return countArray;
    }



    // x = 0 00  x= 1  01  x=2 10 x=3 11
    public static int getCount(int c, int b, int x) {

        if (c == 1) {
            if (x == 2) {
                return 1;
            } else {
                return 0;
            }
        }

        if (b == 1) {
            if (x == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        if (c == 0 && b == 0) {
            return 0;
        }
        if (x == 0) {
            return 0;
        }
        if (c == 1 && b == 1) {
            if (x == 3) {
                return 2;
            } else {
                return 1;
            }
        }
        return 0;
    }
}

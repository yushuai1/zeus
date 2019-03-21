package com.工作测试.bit数组相识度;

public class Test {

    private static byte[] gByte() {
        byte[] b = new byte[512];
        for (int i = 0; i < 512; i++) {
            b[i] = (byte) (Math.random() > 0.5 ? 1 : 0);
        }
        return b;
    }

    public static void main(String[] as) {

        byte[] byte1 = gByte();
        byte[] byte2 = gByte();

        long t1 = System.currentTimeMillis();


        for (int k = 0; k < 500000; k++) {
            for (int a = 0; a < 19; a++) {
                int count = 0;
                for (int i = 0; i < 512-4; i=i+5) {
                    if (byte1[i] == 1 && byte2[i] == 1) {
                        count = count + 1;
                    }
                    if (byte1[i+1] == 1 && byte2[i+1] == 1) {
                        count = count + 1;
                    }
                    if (byte1[i+2] == 1 && byte2[i+2] == 1) {
                        count = count + 1;
                    }
                    if (byte1[i+3] == 1 && byte2[i+3] == 1) {
                        count = count + 1;
                    }
                    if (byte1[i+4] == 1 && byte2[i+4] == 1) {
                        count = count + 1;
                    }
//                    if (byte1[i+5] == 1 && byte2[i+5] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+6] == 1 && byte2[i+6] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+7] == 1 && byte2[i+7] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+8] == 1 && byte2[i+8] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+9] == 1 && byte2[i+9] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+10] == 1 && byte2[i+10] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+11] == 1 && byte2[i+11] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+12] == 1 && byte2[i+12] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+13] == 1 && byte2[i+13] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+14] == 1 && byte2[i+14] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+15] == 1 && byte2[i+15] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+16] == 1 && byte2[i+16] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+17] == 1 && byte2[i+17] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+18] == 1 && byte2[i+18] == 1) {
//                        count = count + 1;
//                    }
//                    if (byte1[i+19] == 1 && byte2[i+19] == 1) {
//                        count = count + 1;
//                    }

                }
            }
        }
        System.out.println("原始方法：time = " + (System.currentTimeMillis() - t1) );


        long t2 = System.currentTimeMillis();
        int count2 = 0;

        for (int k = 0; k < 50 * 10000; k++) {
            for (int q = 0; q < 10; q++) {
                for (int i = 0; i < 512; i++) {
                    if ((byte1[i] & byte2[i]) == 1) {
                        count2 = count2 + 1;
                    }
                }
            }
        }
        System.out.println("&方法：time = " + (System.currentTimeMillis() - t2) + " 相似个数：" + count2);


        long t3 = System.currentTimeMillis();
        int count3 = 0;
        int h = 0;
        while (true) {
            h++;
            for (int m = 0; m < 10; m++) {
                for (int i = 0; i < 512; i++) {
                    if (byte1[i] == 1 && byte2[i] == 1) {
                        count3 = count3 + 1;
                    }
                }
            }
            if (h > 500000) {
                break;
            }
        }


        System.out.println("while方法：time = "+(System.currentTimeMillis()-t3)+" 相似个数："+count3);


}
}

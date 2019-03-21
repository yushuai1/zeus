package com.工作测试.bit数组相识度;

public class TTT {
    private static byte[] gByte() {
        byte[] b = new byte[512];
        for (int i = 0; i < 512; i++) {
            b[i] = (byte) (Math.random() > 0.5 ? 1 : 0);
        }

        return b;
    }

    public static void main(String[] args) {

        byte[] b1 = gByte();

        byte[] b2 = gByte();


        long count = 0;
        boolean x;
        long start = System.currentTimeMillis();
        for (int j = 0; j < 500000; j++) {
            com19(b1, b2);
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start + "    count = " + count);
    }

    private static void com19(byte[] a, byte[] b) {
        for (int k = 0; k < 19; k++) {
            int xx = compare(a, b);
        }
    }

    private static int compare(byte[] a, byte[] b) {
        int count = 0;
        for (int i = 0; i < 512; i++) {
            if (a[i] == 0 || b[i] == 0) {
                continue;
            }
            count++;
        }
        return count;
    }
}

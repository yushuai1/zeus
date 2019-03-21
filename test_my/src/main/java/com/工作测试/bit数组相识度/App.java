package com.工作测试.bit数组相识度;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        System.out.println("创建比对人虹膜组……");
        List<byte[]> a = personIris();

        System.out.println("开始构造虹膜库……");
        List<byte[]> lib = irisLibrary();

        System.out.println("开始执行比对……");
        long start = System.currentTimeMillis();
        int libSize = lib.size();
        int personSize = a.size();


        Set<Integer> scoerSet = new HashSet<>();

        int count = 0;


        for (int i = 0; i < libSize; i++) { // 遍历虹膜库
            for (int p = 0; p < personSize; p++) { // 遍历人员虹膜组
                int score = compare(a.get(p), lib.get(i));
                scoerSet.add(score);
            }
            count++;
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start + "    count = " + count);
    }

    /**
     * 虹膜按位比
     *
     * @param a
     * @param b
     * @return
     */
    private static int compare(byte[] a, byte[] b) {
        int count = 0;
        for (int i = 0; i < 512; i++) {
            if ((a[i] & b[i]) != 1) {
                continue;
            }
            count++;
        }
        return count;
    }

    /**
     * 构造人员多条虹膜
     *
     * @return
     */
    private static List<byte[]> personIris() {
        List<byte[]> lib = new ArrayList<byte[]>();
        for (int i = 0; i < 19; i++) {
            lib.add(gByte());
        }

        return lib;
    }

    /**
     * 构造虹膜比对库
     *
     * @return
     */
    private static List<byte[]> irisLibrary() {
        List<byte[]> lib = new ArrayList<byte[]>();
        for (int i = 0; i < 500000; i++) {
            lib.add(gByte());
        }

        return lib;
    }

    /**
     * 生成单条特征
     *
     * @return
     */
    private static byte[] gByte() {
        byte[] b = new byte[512];
        for (int i = 0; i < 512; i++) {
            b[i] = (byte) (Math.random() > 0.5 ? 1 : 0);
        }

        return b;
    }
}

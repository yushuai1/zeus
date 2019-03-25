package com.设计模式.监听等待;

public class TestMy {

    public static void main(String[] args) {
        for (int j = 0; j < 10000; j++) {
            Test t = new Test();
            int s = t.getY(1);
        }
    }

}

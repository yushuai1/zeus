package com.设计模式.监听等待;

public abstract class AbsTest {

    protected int getResult(int i){
        while (true){
            i++;
            if (i>100){
                return i;
            }

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

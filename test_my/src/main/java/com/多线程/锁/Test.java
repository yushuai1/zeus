package com.多线程.锁;

public class Test implements Runnable {

    public  int i = 0;
    @Override
    public void run() {

        while (true){
            synchronized (this){
                if (i<100){
                    i++;
                    System.out.println(" i= "+i);
                }else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Test d = new Test();
        Thread thread1 = new Thread(d);
        Thread thread2 = new Thread(d);
        thread1.start();
        thread2.start();
    }
}

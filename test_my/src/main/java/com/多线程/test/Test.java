package com.多线程.test;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private int start = 0;
    private Integer lock = new Integer(1);
    final static int count = 100;

    public static void main(String[] args) {
        new Test().stat();
    }

    public void stat() {
        new Thread(new yu(), "one").start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new yu(), "two").start();
    }

    class yu implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (start <= 100) {

                    System.out.println(Thread.currentThread().getName() + "---" + start++);
                    lock.notifyAll();
                    try {
                        if (start <= 100) {
                            lock.wait();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}

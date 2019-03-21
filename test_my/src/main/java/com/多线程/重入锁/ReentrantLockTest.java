package com.多线程.重入锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();
    private boolean flag = true;


    public Object get(int timeout) throws Exception {
        if (timeout <= 0) {
            timeout = 1000*30;
        }
        if (flag) {
            long start = System.currentTimeMillis();
            lock.lock();
            System.out.println("加锁");
            try {
                while (flag) {
                    System.out.println("锁等待开始");
                    done.await(timeout, TimeUnit.MILLISECONDS);
                    System.out.println("锁等待结束");
                    if (flag || System.currentTimeMillis() - start > timeout) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
                System.out.println("解锁");
            }
            if (flag) {
                throw new RuntimeException("超时！");
            }
        }
        return "jakshdakshda";
    }

    private void doReceived() {
        lock.lock();
        System.out.println("第二次加锁");
        try {
            if (done != null) {
                done.signal();
            }
        } finally {
            lock.unlock();
            System.out.println("第二次解锁");
        }

    }

    public static void main(String[] as) throws Exception {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
                Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    System.out.println("11111111111111111111");
                    System.out.println(reentrantLockTest.get(1000*60));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        Thread.sleep(1);
        Thread thread1 = new Thread(){
            @Override
            public void run(){
                try {
                    System.out.println("222222222222222");
                    reentrantLockTest.doReceived();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();


    }
}

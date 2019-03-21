package com.lock.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition {


    public static void main(String[] args) {

        final ReentrantLock reentrantLock = new ReentrantLock();

        final Condition condition = reentrantLock.newCondition();

        Thread thread = new Thread(
                (Runnable) () -> {
                    try {
                        reentrantLock.lock();
                        System.out.println("我要等一个信号" + Thread.currentThread().getName());
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("拿到一个信号！" + Thread.currentThread().getName());
                    reentrantLock.unlock();

                }, "线程1"
        );
        thread.start();


        Thread thread1 = new Thread(
                (Runnable) () -> {

                    reentrantLock.lock();
                    System.out.println("我拿到了锁"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    condition.signalAll();

                    System.out.println("我发了一个信号！"+Thread.currentThread().getName() );
                    reentrantLock.unlock();

                }, "线程2"
        );
        thread1.start();
    }
}

package com.多线程.锁;

import java.util.concurrent.atomic.AtomicReference;

public class 自旋锁 {

    //AtomicReference 原子引用
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        boolean flg = sign.compareAndSet(null,thread);
        System.out.println("线程自悬开始！"+flg+"--"+thread);
        while (!flg){

        }
        System.out.println("线程自悬结束！");
    }

    public void unlock(){

        Thread thread = Thread.currentThread();
        // 可以支持并发访问，set的时候进行对比判断，如果当前值和操作之前一样则返回false，否则表示数据没有 变化
        boolean flg = sign.compareAndSet(thread,null);
        System.out.println("解除自悬！"+flg);
    }

    public void test() throws InterruptedException {
        System.out.println("-----------------");
        Thread.sleep(1000);
    }
    public static void main(String[] asd) throws InterruptedException {

        自旋锁 zxs = new 自旋锁();

        zxs.lock();
        zxs.test();
        zxs.unlock();
        zxs.lock();
        zxs.test();

    }
}

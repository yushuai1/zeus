package com.lambda.runable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Runnable接口的实现仅仅是说明这个类中有个run()方法
 */
public class Test {

    /**
     * 线程池
     */
    public static ExecutorService executor = new ThreadPoolExecutor(4, 4,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(3));

    public static void main(String[] args){
        new Test().run("yes");
        new Test().run();
    }

    public void run(){
        new Thread( () -> run("no"),"nihao" ).start();

    }

    public void run(String name) {
        executor.execute(() -> testTurn(name));
    }

    public void testTurn(String name) {

        System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId());
        System.out.println(name);
    }
}

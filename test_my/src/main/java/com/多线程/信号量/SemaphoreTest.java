package com.多线程.信号量;

import java.util.concurrent.*;

public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = new ThreadPoolExecutor(4, 4,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(30));

    private static Semaphore s = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        long t1 = System.currentTimeMillis();
        for (int i=0;i<50000000;i++){
            s.acquire();
            s.release();
        }
        System.out.println("time is "+(System.currentTimeMillis()-t1));




//        for (int i = 0; i < THREAD_COUNT; i++) {
//            threadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        s.acquire();
//                        System.out.println("save data");
//                        s.release();
//                    } catch (InterruptedException e) {
//                    }
//
//
//                }
//            });
//        }
        threadPool.shutdown();
    }
}


package com.多线程.线程数据交换;

import java.util.concurrent.*;

public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<String>();

    private static ExecutorService threadPool =  new ThreadPoolExecutor(1, 2,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(30));

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";// A录入银行流水数据
//                    String B = exgr.exchange(A,1000,TimeUnit.SECONDS);
                    String B = "asdf";
                    System.out.println("A和B数据是否一致：" + A.equals(B) + "，A录入的是："
                            + A + "，B录入是：" + B);
                    Thread.sleep(100*1000L);
                } catch (Exception e) {
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B";// B录入银行流水数据
//                    String A = exgr.exchange(B);
                    String A = "asd";
                    System.out.println("A和B数据是否一致：" + A.equals(B) + "，A录入的是："
                            + A + "，B录入是：" + B);
                } catch (Exception e) {
                }
            }
        });
//        threadPool.shutdown();
    }
}

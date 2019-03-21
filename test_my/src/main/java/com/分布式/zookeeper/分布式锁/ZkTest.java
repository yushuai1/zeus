package com.分布式.zookeeper.分布式锁;


public class ZkTest {

    public static void main(String[] args) {
        Runnable task1 = new Runnable(){
            @Override
            public void run() {
                DistributedLock lock = null;
                try {

                    System.out.println("1111111111111111111111111111111111111");
                    lock = new DistributedLock("test12");
                    System.out.println("2222222222222222222222222222222222222222222");
                    lock.lock();
                    System.out.println("333333333333333333333333333333333333333333");
                    Thread.sleep(3000);
                    System.out.println("===Thread " + Thread.currentThread().getId() + " running");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(lock != null){
                        lock.unlock();
                    }

                }

            }

        };
        new Thread(task1).start();


//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
//        ConcurrentTest.ConcurrentTask[] tasks = new ConcurrentTest.ConcurrentTask[60];
//        for(int i=0;i<tasks.length;i++){
//            ConcurrentTest.ConcurrentTask task3 = new ConcurrentTest.ConcurrentTask(){
//                @Override
//                public void run() {
//                    DistributedLock lock = null;
//                    try {
//                        lock = new DistributedLock("128.0.0.1:2183","test2");
//                        lock.lock();
//                        System.out.println("Thread " + Thread.currentThread().getId() + " running");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    finally {
//                        lock.unlock();
//                    }
//
//                }
//            };
//            tasks[i] = task3;
//        }
//        new ConcurrentTest(tasks);
    }
}


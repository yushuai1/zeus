package com.多线程.condition;

import java.util.Random;

class Consumer implements Runnable{
    private Buffer buffer;
    Consumer(Buffer b){
        buffer=b;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者消费掉---"+buffer.take());
        }
    }
}


package com.多线程.condition;


import java.util.Random;

class Producer implements Runnable {
    private Buffer buffer;

    Producer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            int kl = new Random().nextInt();
            try {
                Thread.sleep(1000*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.put(kl);
            System.out.println("加入------" + kl);
        }
    }
}

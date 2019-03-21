package com.多线程.condition;

public class Main{
    public static void main(String[] arg){

        //buffer长度为10
        Buffer<Integer> buffer=new Buffer(3);


        Producer producer=new Producer(buffer);
        Consumer consumer=new Consumer(buffer);

        //三个生产者和三个消费者
        for(int i=0;i<1;i++){
            new Thread(producer,"producer-"+i).start();
        }
        for(int i=0;i<1;i++){
            new Thread(consumer,"consumer-"+i).start();
        }
    }
}
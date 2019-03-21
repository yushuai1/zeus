package com.多线程.condition;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Buffer<T> {
    private  final Lock lock;
    private  final Condition notFull;
    private  final Condition notEmpty;
    private int maxSize;
    private List<T> storage;
    Buffer(int size){
        //使用锁lock，并且创建两个condition，相当于两个阻塞队列
        lock=new ReentrantLock();
        //是否慢了
        notFull=lock.newCondition();
        //是否为空
        notEmpty=lock.newCondition();

        maxSize=size;
        storage=new LinkedList<>();
    }
    public void put(T t)  {
        lock.lock();
        try {
            //如果队列满了
            while (storage.size() ==maxSize ){
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                notFull.await();//阻塞生产线程
            }
            storage.add(t);
            System.out.print(Thread.currentThread().getName()+": put:"+storage.size()+ "\n");
//            Thread.sleep(1000);
            notEmpty.signalAll();//唤醒消费线程
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public  T take() {
        lock.lock();
        T d = null;
        try {
            //如果队列为空
            while (storage.size() ==0 ){
                System.out.print(Thread.currentThread().getName()+": wait \n");;
                notEmpty.await();//阻塞消费线程
            }
            d=((LinkedList<T>)storage).poll();
            System.out.print(Thread.currentThread().getName()+": take:"+storage.size()+ "\n");
//            Thread.sleep(1000);
            notFull.signalAll();//唤醒生产线程

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return d;
    }
}


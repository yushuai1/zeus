package com.api.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheUtil {

    private Map<String, Object> cache = new HashMap<String, Object>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key) {
        //上读锁
        rwl.readLock().lock();
        Object value = null;
        try {
            //先查询内部存储器中有没有要的值
            value = cache.get(key);
            //如果没有，就去数据库中查询，并将查到的结果存入内部存储器中
            if (value == null) {
                //释放读锁、上写锁
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    //再次进行判断，防止多个写线程堵在这个地方重复写
                    if (value == null) {
                        System.out.println("read data from database");
                        value = "张三";
                        cache.put(key, value);
                    }
                } finally {
                    //设置完成 释放写锁
                    rwl.writeLock().unlock();
                }
                //恢复读写状态
                rwl.readLock().lock();
            }
        } finally {
            rwl.readLock().unlock(); //释放读锁
        }
        return value;
    }

    //写数据
    public void put(String key, Object data) {

        rwl.writeLock().lock();
        try {
            Thread.sleep((long) (Math.random() * 1000));
            cache.put(key, data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    public Object get(String key) {
        rwl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        String key = "name";
        CacheUtil cacheDemo = new CacheUtil();
        System.out.println(cacheDemo.getData(key)); //从数据库获取数据
        System.out.println(cacheDemo.getData(key)); //从缓存获取数据
        System.out.println(cacheDemo.getData(key)); //从缓存获取数据
    }

}

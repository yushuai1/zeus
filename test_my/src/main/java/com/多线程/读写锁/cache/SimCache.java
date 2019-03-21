package com.多线程.读写锁.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存
 */
public class SimCache <K,V>{

    public  SimCache() {
    }

    private final  SimFilter filter = new  SimFilter();

    private final Map<K, V> cache = new HashMap<K, V>();

    private final ReadWriteLock rwl = new ReentrantReadWriteLock();

    private SimDataSql<K,V> simDataSql;

    public V getData(K key) {
        //上读锁
        rwl.readLock().lock();
        V value = null;
        try {
            //先查询内部存储器中有没有要的值
            value =  cache.get(key);
            //如果没有，就去数据库中查询，并将查到的结果存入内部存储器中
            if (value == null) {
                //如果值为空的话应该先查过滤器，如果过滤器存在,证明已经查过了不存在的，就没必要再查了
                if (filter.contains(key+"")){
                    return value;
                }
                //释放读锁、上写锁
                rwl.readLock().unlock();

                rwl.writeLock().lock();
                try {
                    //再次进行判断，防止多个写线程堵在这个地方重复写
                    value = cache.get(key);
                    if (value == null) {
                        System.out.println("从数据库读取数据****");
                        value = simDataSql.getData(key);
                        //如果数据库中没有进入过滤器
                        if (value == null){
                            filter.add(key+"");
                        }else {
                            cache.put(key, value);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    rwl.writeLock().unlock();
                }

                rwl.readLock().lock();
            }
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }

    //写数据
    public void put(K key, V data) {

        rwl.writeLock().lock();
        try {
            //如果新增加，就应该把过滤器中的值删除，防止被过滤掉，好像没必要
//            filter.remove(key);
            cache.put(key, data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    //删除数据
    public void remove(K key) {

        rwl.writeLock().lock();
        try {
            //如果删除本地缓存，就应该把过滤器中的值删除，防止被过滤掉
            filter.remove(key+"");
            cache.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    public V get(K key) {
        rwl.readLock().lock();
        V value = null;
        try {
            value = cache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }
}

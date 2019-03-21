package com.多线程.读写锁.cache;

public interface SimDataSql<K,V> {

    public abstract V getData(K m);
}

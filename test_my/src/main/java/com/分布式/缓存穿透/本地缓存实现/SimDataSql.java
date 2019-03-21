package com.分布式.缓存穿透.本地缓存实现;

public interface SimDataSql<K,V> {

    public abstract V getData(K m);
}

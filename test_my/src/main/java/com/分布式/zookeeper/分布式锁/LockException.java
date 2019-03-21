package com.分布式.zookeeper.分布式锁;

public class LockException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public LockException(String e){
        super(e);
    }
    public LockException(Exception e){
        super(e);
    }
}
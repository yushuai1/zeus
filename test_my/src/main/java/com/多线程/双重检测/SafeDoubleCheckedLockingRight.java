package com.多线程.双重检测;

/**
 * volatile 在jdk1.5之后语义增强，前后加入内存屏障，所以禁止2和3重排
 */
public class SafeDoubleCheckedLockingRight {

    private volatile static TestDomain instance;

    public static TestDomain getInstance() {
        if (instance == null) {
            synchronized (SafeDoubleCheckedLockingRight.class) {
                if (instance == null) {
                    instance = new TestDomain(); // instance为volatile，现在没问题了
                }
            }

        }
        return instance;
    }
}


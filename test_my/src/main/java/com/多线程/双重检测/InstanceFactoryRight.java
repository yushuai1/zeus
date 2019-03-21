package com.多线程.双重检测;

/**
 * JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前），会执行类的初始化。
 * 在执行类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化
 */
public class InstanceFactoryRight {
    private static class InstanceHolder {
        public static TestDomain instance = new TestDomain();
    }
    public static TestDomain getInstance() {
        return InstanceHolder.instance ;// 这里将导致InstanceHolder类被初始化
    }

    public static void main(String[] args) {

    }
}



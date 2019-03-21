package com.多线程.双重检测;


/**
 * 当执行到第4步骤的时候，有可能不为空但是对象还没有初始化
 * 因为第7行中2和3有可能发生重排
 */
public class DoubleCheckedLockingError { // 1
    private static TestDomain instance; // 2
    public static TestDomain getInstance() { // 3
        if (instance == null) { // 4:第一次检查
            synchronized (DoubleCheckedLockingError.class) { // 5:加锁
                if (instance == null) // 6:第二次检查
                {

                    /**
                     * memory = allocate();　　// 1：分配对象的内存空间
                     * ctorInstance(memory);　 // 2：初始化对象
                     * instance = memory;　　  // 3：设置instance指向刚分配的内存地址
                     */
                    instance = new TestDomain();// 7:问题的根源出在这里
                }
            } // 8
        } // 9
        return instance; // 10
    } // 11
}

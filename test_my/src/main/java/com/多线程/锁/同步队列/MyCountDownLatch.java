package com.多线程.锁.同步队列;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyCountDownLatch {
    /**
     * 基于AQS的内部Sync
     * 使用AQS的state来表示计数count.
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count) {
            // 使用AQS的getState()方法设置状态
            setState(count);
        }

        int getCount() {
            // 使用AQS的getState()方法获取状态
            return getState();
        }

        // 覆盖在共享模式下尝试获取锁
        @Override
        protected int tryAcquireShared(int acquires) {
            // 这里用状态state是否为0来表示是否成功，为0的时候可以获取到返回1，否则不可以返回-1
            return (getState() == 0) ? 1 : -1;
        }

        // 覆盖在共享模式下尝试释放锁
        @Override
        protected boolean tryReleaseShared(int releases) {
            // 在for循环中Decrement count直至成功;
            // 当状态值即count为0的时候，返回false表示 signal when transition to zero
            for (;;) {
                int c = getState();
                if (c == 0){
                    return false;
                }

                int nextc = c-1;
                if (compareAndSetState(c, nextc)){
                    return nextc == 0;
                }

            }
        }
    }

    private final Sync sync;
    public MyCountDownLatch(int count) {
        if (count < 0)
        {
            throw new IllegalArgumentException("count < 0");
        }
        this.sync = new Sync(count);
    }

    // 让当前线程阻塞直到计数count变为0，或者线程被中断
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    // 阻塞当前线程，除非count变为0或者等待了timeout的时间。当count变为0时，返回true
    public boolean await(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }

    // count递减
    public void countDown() {
        sync.releaseShared(1);
    }

    // 获取当前count值
    public long getCount() {
        return sync.getCount();
    }

    @Override
    public String toString() {
        return super.toString() + "[Count = " + sync.getCount() + "]";
    }
}
package com.lock.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SimpleLock extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int unused) {

        assert unused == 1; // 这里限定只能为1个量
        //state为0才设置为1，不可重入！
        if (compareAndSetState(0, 1)) {

            //设置为当前线程独占资源
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int unused) {
        assert unused == 1; // 限定为1个量
        if (getState() == 0)//既然来释放，那肯定就是已占有状态了。只是为了保险，多层判断！
        {
            throw new IllegalMonitorStateException();
        }
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public boolean tryLock() {
        return tryAcquire(1);
    }

    public void unlock() {
        release(1);
    }

    public boolean isLocked() {
        return isHeldExclusively();
    }

}

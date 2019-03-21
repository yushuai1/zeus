package com.分布式.zookeeper.分布式锁;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 DistributedLock lock = null;
 try {
 lock = new DistributedLock("128.0.0.1:2182","test");
 lock.lock();
 //do something...
 } catch (Exception e) {
 e.printStackTrace();
 }
 finally {
 if(lock != null)
 lock.unlock();
 }
 * @author xueliang
 *
 */
public class DistributedLock implements Watcher{
    private ZooKeeper zk;
    private String root = "/lockss";//根
    private String lockName;//竞争资源的标志
    private String waitNode;//等待前一个锁
    private String myZnode;//当前锁
    private CountDownLatch latch;//计数器
    private static int sessionTimeout = 30000;
    private List<Exception> exception = new ArrayList<Exception>();
    private CountDownLatch connectedSignal = new CountDownLatch(1);
    public ZooKeeper getInstance() throws Exception {

        ZooKeeper zk = new ZooKeeper("10.3.1.166:2181",
                sessionTimeout, this);
        connectedSignal.await();
        return zk;
    }


    public DistributedLock(String lockName){
        this.lockName = lockName;
        // 创建一个与服务器的连接
        try {
            zk = getInstance();
            Stat stat = zk.exists(root, false);
            if(stat == null){
                zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            exception.add(e);
        }
    }



    public void lock() {
        if(exception.size() > 0){
            throw new LockException(exception.get(0));
        }
        try {
            if(this.tryLock()){
                System.out.println("Thread " + Thread.currentThread().getId() + " " +myZnode + " get lock true");
                return;
            }
            else{
                waitForLock(waitNode, sessionTimeout);//等待锁
            }
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }
    }


    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if(lockName.contains(splitStr)){
                throw new LockException("lockName can not contains \\u000B");
            }

            myZnode = zk.create(root + "/" + lockName + splitStr,
                    new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(myZnode + " is created ");

            List<String> subNodes = zk.getChildren(root, false);

            List<String> lockObjNodes = new ArrayList<String>();
            for (String node : subNodes) {
                String _node = node.split(splitStr)[0];
                if(_node.equals(lockName)){
                    lockObjNodes.add(node);
                }
            }
            Collections.sort(lockObjNodes);
            System.out.println(myZnode + "==" + lockObjNodes.get(0));
            if(myZnode.equals(root+"/"+lockObjNodes.get(0))){
                return true;
            }
            String subMyZnode = myZnode.substring(myZnode.lastIndexOf("/") + 1);
            waitNode = lockObjNodes.get(Collections.binarySearch(lockObjNodes, subMyZnode) - 1);
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }
        return false;
    }

    private boolean waitForLock(String lower, long waitTime) throws InterruptedException, KeeperException {
        Stat stat = zk.exists(root + "/" + lower,true);
        if(stat != null){
            System.out.println("Thread " + Thread.currentThread().getId() + " waiting for " + root + "/" + lower);
            this.latch = new CountDownLatch(1);
            this.latch.await(waitTime, TimeUnit.MILLISECONDS);
            this.latch = null;
        }
        return true;
    }

    public void unlock() {
        try {
            System.out.println("unlock " + myZnode);
            zk.delete(myZnode,-1);
            myZnode = null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("**************************************");
        if  (event.getState()  ==  Watcher.Event.KeeperState.SyncConnected) {
            if (event.getType()==Watcher.Event.EventType.None){
                System.out.println("已经触发了链接事件！"+"  路径:"+event.getPath()
                        +"---"+event.getType()+"---"+event.getState());
                connectedSignal.countDown();
            }

            if (event.getType()==Watcher.Event.EventType.NodeDeleted){
                System.out.println("已经触发了删除事件！ 路径:"+event.getPath());
                latch.countDown();
            }

        }

    }
}

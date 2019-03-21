package com.分布式.zookeeper.分布式锁;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 临时节点下不能创建子节点
 */

public class Test {
    private static String connectionString = "10.3.1.166:2181";
    private static int sessionTimeout = 30000;
    private static String path = "/roottest2";
    public static ZooKeeper getInstance() throws IOException, InterruptedException {
        //--------------------------------------------------------------
        // 为避免连接还未完成就执行zookeeper的get/create/exists操作引起的（KeeperErrorCode = ConnectionLoss)
        // 这里等Zookeeper的连接完成才返回实例
        //--------------------------------------------------------------
        final CountDownLatch connectedSignal = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if  (event.getState()  ==  Event.KeeperState.SyncConnected) {
                    System.out.println("已经触发了" + event.getType() + "事件！"+"  路径:"+event.getPath());
                    connectedSignal.countDown();
                }
            }
        });
        connectedSignal.await();
        return zk;
    }

    public static void main(String[] as) throws Exception {
        ZooKeeper zk = getInstance();
        // 创建一个目录节点

        zk.create(path, "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        // 创建一个子目录节点
        zk.create(path+"/testChildPathOne", "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        byte[] bytes = zk.getData(path,false,null);
        System.out.println("11111111111111111111  "+new String(bytes));
        // 取出子目录节点列表
        List<String> list = zk.getChildren(path,true);
        System.out.println("2222222222222222222  "+list);
        // 修改子目录节点数据
        Stat stat =  zk.setData(path+"/testChildPathOne","modifyChildDataOne".getBytes(),-1);
        System.out.println("33333333333333333333  "+stat.getAversion()+"--"+stat.toString());
        Stat stat1 = zk.exists(path,true);
        System.out.println("4444444444444444   目录节点状态：["+stat1+"]");
        // 创建另外一个子目录节点
        String ss = zk.create(path+"/testChildPathTwo", "testChildDataTwo".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println("5555555555555555  "+ss);
        byte[] bytes1 = zk.getData(path+"/testChildPathTwo",true,null);
        System.out.println("6666666666666666  "+new String(bytes1));
        // 删除子目录节点
        zk.delete(path+"/testChildPathTwo",-1);
        zk.delete(path+"/testChildPathOne",-1);
        // 删除父目录节点
        zk.delete(path,-1);
        // 关闭连接
        zk.close();
    }
}

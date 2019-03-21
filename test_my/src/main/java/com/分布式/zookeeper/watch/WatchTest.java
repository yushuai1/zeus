package com.分布式.zookeeper.watch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WatchTest {
    private static String connectionString = "127.0.0.1:2181";
    private static int sessionTimeout = 30000;
    private static String path = "/roottest8";

    public static ZooKeeper getInstance() throws IOException, InterruptedException {

        final CountDownLatch connectedSignal = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(connectionString, sessionTimeout, new WatchEventListenner(connectedSignal));
        connectedSignal.await();
        return zk;
    }

    public static void main(String[] as) throws Exception {
        ZooKeeper zk = getInstance();

        zk.create(path, "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.getChildren(path,true);
        System.out.println("1111111111111111111");
        zk.setData(path,"modifyChildDataOne".getBytes(),-1);
        zk.getChildren(path,true);
        System.out.println("2222222222222222");
        zk.create(path+"/testChildPathOne", "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.getChildren(path,true);
        System.out.println("333333333333333333");
        zk.setData(path+"/testChildPathOne", "testCdDataOne".getBytes(),-1);
        zk.getChildren(path,true);
        System.out.println("4444444444444444444444");
        zk.delete(path+"/testChildPathOne",-1);
        zk.getData(path,true,null);
        System.out.println("55555555555555555");
        zk.delete(path,-1);
//        exits(zk);
//        // 创建一个子目录节点
//        getdata(zk);
//        zk.delete(path+"/testChildPathOne",0);
//        zk.delete(path,0);
//        System.out.println("11111111111111111111  "+new String(bytes));
//        // 取出子目录节点列表
//        List<String> list = zk.getChildren(path,true);
//        System.out.println("2222222222222222222  "+list);
//        // 修改子目录节点数据
//        Stat stat =  zk.setData(path+"/testChildPathOne","modifyChildDataOne".getBytes(),-1);
//        System.out.println("33333333333333333333  "+stat.getAversion()+"--"+stat.toString());
//        Stat stat1 = zk.exists(path,true);
//        System.out.println("4444444444444444   目录节点状态：["+stat1+"]");
//        // 创建另外一个子目录节点
//        String ss = zk.create(path+"/testChildPathTwo", "testChildDataTwo".getBytes(),
//                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
//        System.out.println("5555555555555555  "+ss);
//        byte[] bytes1 = zk.getData(path+"/testChildPathTwo",true,null);
//        System.out.println("6666666666666666  "+new String(bytes1));
//        // 删除子目录节点
//        zk.delete(path+"/testChildPathTwo",-1);
//        zk.delete(path+"/testChildPathOne",-1);
//        // 删除父目录节点
//        zk.delete(path,-1);
//        // 关闭连接
        zk.close();
    }

    private static void getdata(ZooKeeper zk) throws KeeperException, InterruptedException {
        zk.create(path, "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.getData(path,true,null);
        zk.setData(path,"modifyChildDataOne".getBytes(),-1);
        zk.getData(path,true,null);
        zk.create(path+"/testChildPathOne", "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.getData(path,true,null);
        zk.setData(path+"/testChildPathOne", "testCdDataOne".getBytes(),-1);
        zk.getData(path,true,null);
        zk.delete(path+"/testChildPathOne",-1);
        zk.getData(path,true,null);
        zk.delete(path,-1);
    }

    private static void exits(ZooKeeper zk) throws KeeperException, InterruptedException {
        zk.exists(path, true);
        zk.create(path, "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        zk.exists(path, true);
        zk.setData(path, "modifyChildDataOne".getBytes(), -1);
        zk.exists(path, true);
        zk.delete(path,-1);
    }
}

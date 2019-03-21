package com.分布式.zookeeper.服务注册;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;

import java.util.concurrent.CountDownLatch;

public class DistributedSystemServer {

    private ZooKeeper zk = null;

    private void getZkClient() throws Exception {
        final CountDownLatch connectedSignal = new CountDownLatch(1);
        // 服务器在需求中并不需要做任何监听
        zk = new ZooKeeper(GlobalConstants.zkhosts,
                GlobalConstants.sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() == Event.EventType.None){
                    connectedSignal.countDown();
                    return;
                }

            }
        });
        connectedSignal.await();
    }

    /**
     * 向zookeeper中的/servers下创建子节点
     *
     * @throws InterruptedException
     * @throws KeeperException
     */
    private void connectZK(String serverName, String port) throws Exception {

        // 先创建出父节点
        if (zk.exists(GlobalConstants.parentZnodePath, false) == null) {
            zk.create(GlobalConstants.parentZnodePath, null,
                    Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 连接zk创建znode
        zk.create(GlobalConstants.parentZnodePath + "/",
                (serverName + ":" + port).getBytes(), Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("server " + serverName + " is online ......");

    }

    // 服务器的具体业务处理功能
    private void handle(String serverName) throws Exception {
        System.out.println("server " + serverName
                + " is waiting for task process......");
        Thread.sleep(Long.MAX_VALUE);

    }

    public static void main(String[] args) throws Exception {

        DistributedSystemServer server = new DistributedSystemServer();

        // 获取与zookeeper通信的客户端连接
        server.getZkClient();

        // 一启动就去zookeeper上注册服务器信息，参数1： 服务器的主机名 参数2：服务器的监听端口
        server.connectZK("128.0.0.3", "6333");

        // 进入业务逻辑处理流程
        server.handle("myservce");

    }

}

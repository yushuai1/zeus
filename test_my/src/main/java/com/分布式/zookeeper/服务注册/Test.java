package com.分布式.zookeeper.服务注册;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Test {

    private static String connectionString = "10.3.1.166:2181";
    private static int sessionTimeout = 30000;

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
                    System.out.println(event.toString()+"--"+event.getState().toString());
                    connectedSignal.countDown();
                }
            }
        });
        connectedSignal.await();
        return zk;
    }

    public static void main(String yuu[]) throws Exception {

        ZooKeeper zooKeeper = getInstance();
        /**
         * 参数一：路径地址
         * 参数二：想要保存的数据，需要转换成字节数组
         * 参数三：ACL访问控制列表（Access control list）,
         *      参数类型为ArrayList<ACL>，Ids接口提供了一些默认的值可以调用。
         *      OPEN_ACL_UNSAFE     This is a completely open ACL
         *                          这是一个完全开放的ACL，不安全
         *      CREATOR_ALL_ACL     This ACL gives the
         *                           creators authentication id's all permissions.
         *                          这个ACL赋予那些授权了的用户具备权限
         *      READ_ACL_UNSAFE     This ACL gives the world the ability to read.
         *                          这个ACL赋予用户读的权限，也就是获取数据之类的权限。
         * 参数四：创建的节点类型。枚举值CreateMode
         *      PERSISTENT (0, false, false)
         *      PERSISTENT_SEQUENTIAL (2, false, true)
         *          这两个类型创建的都是持久型类型节点，回话结束之后不会自动删除。
         *          区别在于，第二个类型所创建的节点名后会有一个单调递增的数值
         *      EPHEMERAL (1, true, false)
         *      EPHEMERAL_SEQUENTIAL (3, true, true)
         *          这两个类型所创建的是临时型类型节点，在回话结束之后，自动删除。
         *          区别在于，第二个类型所创建的临时型节点名后面会有一个单调递增的数值。
         * 最后create()方法的返回值是创建的节点的实际路径
         */
        zooKeeper.create("/testRoot", "testRoot".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        Stat stat = new Stat();
        byte[] bytes = zooKeeper.getData("/testRoot",true,stat);
        System.out.println(new String(bytes));
        zooKeeper.close();
    }
}

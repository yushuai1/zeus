package com.分布式.zookeeper.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;


//delete()同时触发父节点的子节点Watch和内容Watch，以及子节点的内容Watch。

public class WatchEventListenner implements Watcher {

    private CountDownLatch countDown;
    public WatchEventListenner(CountDownLatch countDown){
        this.countDown = countDown;
    }
    @Override
    public void process(WatchedEvent event) {

//        Disconnected(0)                  客户端处于断开连接的状态，并且没有和Zookeeper集群中任何服务器连接。
//        SyncConnected(3)                客户端处于连接的状态，也就是说客户端连接到了一台server
//        AuthFailed(4)                     验证失败的状态
//        ConnectedReadOnly(5)              客户端连接到一个只读Server的状态。
//        SaslAuthenticated(6)             用于通知客户端他们是SASL认证，以至于他们能够SASL认证的权限通过操作Zookeeper。
//        Expired(-112)            会话超时状态

        if  (event.getState()  ==  Event.KeeperState.SyncConnected) {
            if (event.getType()==Event.EventType.None){
                System.out.println("已经触发了链接事件！"+"  路径:"+event.getPath()
                        +"---"+event.getType()+"---"+event.getState());
            }
            if (event.getType()==Event.EventType.NodeCreated){
                System.out.println("已经触发了创建事件！ 路径:"+event.getPath());
            }
            if (event.getType()==Event.EventType.NodeDeleted){
                System.out.println("已经触发了删除事件！ 路径:"+event.getPath());
            }
           //  Znode数据内容更新事件。其实本质上该事件只关注dataVersion版本号，
            // 但是只要调用了更新接口dataVersion就会有变更。
            if (event.getType()==Event.EventType.NodeDataChanged){
                System.out.println("已经触发了更新事件！ 路径:"+event.getPath());
            }
            //Znode子节点改变事件，只关注子节点的个数变更，子节点内容有变更是不会通知的。
            if (event.getType()==Event.EventType.NodeChildrenChanged){
                System.out.println("已经触发了子节点变更事件非内容！ 路径:"+event.getPath());
            }

            // 为避免连接还未完成就执行zookeeper的get/create/exists操作引起的（KeeperErrorCode = ConnectionLoss)
            // 这里等Zookeeper的连接完成才返回实例
            countDown.countDown();
        }
        if (event.getState() == Event.KeeperState.Disconnected){
            System.out.println("客户端断开链接！");
        }
        if (event.getState() == Event.KeeperState.AuthFailed){
            System.out.println("客户端认证失败！");
        }
        if (event.getState() == Event.KeeperState.Expired){
            System.out.println("客户端超时！");
        }
    }
}
